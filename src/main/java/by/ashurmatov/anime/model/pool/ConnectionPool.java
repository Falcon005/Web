package by.ashurmatov.anime.model.pool;

public class ConnectionPool {
    private static ConnectionPool instance;
    private ConnectionPool(){

    }

    public static ConnectionPool getInstance() {
        instance = new ConnectionPool();
        return instance;
    }
}
