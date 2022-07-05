package by.ashurmatov.anime.controller.listener;

import by.ashurmatov.anime.pool.ConnectionPool;
import jakarta.servlet.ServletContextEvent;
import jakarta.servlet.ServletContextListener;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class ServletContextListenerImpl implements ServletContextListener {
    private static final Logger logger = LogManager.getLogger(ServletContextListenerImpl.class);
    @Override
    public void contextInitialized(ServletContextEvent sce) {
        ConnectionPool connectionPool = ConnectionPool.INSTANCE;
        logger.info("<<<Context is initialized>>> " + sce.getServletContext().getServerInfo(),connectionPool.toString());
    }

    @Override
    public void contextDestroyed(ServletContextEvent sce) {
        ConnectionPool.INSTANCE.destroyPool();
        logger.info("<<<Context is destroyed>>> " + sce.getServletContext().getContextPath());
    }
}
