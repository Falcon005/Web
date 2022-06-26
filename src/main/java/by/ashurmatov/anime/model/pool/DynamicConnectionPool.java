package by.ashurmatov.anime.model.pool;

import org.apache.logging.log4j.Level;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.LinkedBlockingQueue;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

public class DynamicConnectionPool {
    private static final Logger logger = LogManager.getLogger(DynamicConnectionPool.class);
    private static final int MIN_POOL_SIZE = 8;
    private static final int MAX_POOL_SIZE = 32;
    private final AtomicInteger currentPoolSize = new AtomicInteger(MIN_POOL_SIZE);

    private static final DynamicConnectionPool instance = new DynamicConnectionPool();

    private final BlockingQueue<ProxyConnection> freeConnections;
    private final BlockingQueue<ProxyConnection> givenAwayConnections;

    private static final Lock locker = new ReentrantLock();


    private DynamicConnectionPool(){
        try{
            DriverManager.registerDriver(new org.postgresql.Driver());
        }catch (SQLException e){

            logger.warn("Error while registering drive", e);
        }

        freeConnections = new LinkedBlockingQueue<>();
        for(int i=0;i<MIN_POOL_SIZE;i++){
            Connection connection;
            try{
                connection = ConnectionCreator.createConnection();
            }catch (SQLException e){
                logger.fatal("Error while getting connection from driver manager",e);
                throw new RuntimeException("Error while getting connection from driver manager", e);
            }
            ProxyConnection proxyConnection = new ProxyConnection(connection);
            freeConnections.offer(proxyConnection);
        }
        givenAwayConnections = new LinkedBlockingQueue<>();
    }

    public static DynamicConnectionPool getInstance(){
        return instance;
    }

    public Connection provideConnection(){
        ProxyConnection connection;
        try{
            connection = freeConnections.take();
            givenAwayConnections.offer(connection);
            locker.lock();
            if(givenAwayConnections.size() == currentPoolSize.get()){
                if(currentPoolSize.get() < MAX_POOL_SIZE){

                    if(currentPoolSize.get() < MAX_POOL_SIZE){
                        for(int i = 0; i < currentPoolSize.get(); i++){
                            Connection newConnection;
                            try {
                                newConnection = ConnectionCreator.createConnection();
                            } catch (SQLException e) {
                                logger.fatal("Error while getting connection from driver manager", e);
                                throw new RuntimeException("Error while getting connection from driver manager", e);
                            }
                            ProxyConnection proxyConnection = new ProxyConnection(newConnection);
                            freeConnections.offer(proxyConnection);
                        }
                        currentPoolSize.set(currentPoolSize.get() * 2);
                    }

                }
            }
            locker.unlock();
        }catch (InterruptedException e) {
            logger.fatal("Can't get connection form connection queue", e);
            throw new RuntimeException("Error while getting connection from queue", e);
        }
        return connection;
    }

    public void releaseConnection(Connection connection) {
        if (connection instanceof ProxyConnection) {
            givenAwayConnections.remove(connection);
            freeConnections.offer((ProxyConnection) connection);
            locker.lock();
            if(givenAwayConnections.size() < currentPoolSize.get() / 4){
                if(currentPoolSize.get() > MIN_POOL_SIZE){
                    if(currentPoolSize.get() > MIN_POOL_SIZE){
                        currentPoolSize.set(currentPoolSize.get() / 2);
                        for(int i = 0; i < currentPoolSize.get(); i++){
                            try {
                                try {
                                    freeConnections.take().realClose();
                                } catch (SQLException e) {
                                    logger.log(Level.ERROR, e);
                                }
                            } catch (InterruptedException e) {
                                logger.warn("Can't take connection from queue", e);
                            }
                        }
                    }
                }

            }
            locker.unlock();
        } else {
            logger.warn("Invalid connection object provided");
        }
    }

    public void destroyPool(){
        for (int i = 0; i < currentPoolSize.get(); i++) {
            try {
                try {
                    freeConnections.take().realClose();
                } catch (SQLException e) {
                    logger.log(Level.ERROR, e);
                }
            } catch (InterruptedException e) {
                logger.warn("Can't take connection from queue", e);
            }
        }
        deregisterDriver();
    }

    private void deregisterDriver(){
        DriverManager.getDrivers().asIterator().forEachRemaining(driver -> {
            try{
                DriverManager.deregisterDriver(driver);
            }catch (SQLException e){
                logger.warn("Can't access database to deregister driver",e);
            }
        });
    }


}
