package by.ashurmatov.anime.controller.listener;

import jakarta.servlet.http.HttpSessionEvent;
import jakarta.servlet.http.HttpSessionListener;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class HttpSessionListenerImpl implements HttpSessionListener {
    private static final Logger logger = LogManager.getLogger(HttpSessionListenerImpl.class);
    @Override
    public void sessionCreated(HttpSessionEvent se) {
        logger.info("---Session is created--- " + se.getSession().getId());
    }

    @Override
    public void sessionDestroyed(HttpSessionEvent se) {
        logger.info("<---Session is destroyed---> " + se.getSession().getId());
    }
}
