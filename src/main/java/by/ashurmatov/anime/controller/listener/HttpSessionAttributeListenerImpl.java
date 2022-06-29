package by.ashurmatov.anime.controller.listener;

import by.ashurmatov.anime.controller.attribute.ParameterName;
import jakarta.servlet.http.HttpSessionAttributeListener;
import jakarta.servlet.http.HttpSessionBindingEvent;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class HttpSessionAttributeListenerImpl implements HttpSessionAttributeListener {
    private static final Logger logger = LogManager.getLogger(HttpSessionAttributeListenerImpl.class);
    @Override
    public void attributeAdded(HttpSessionBindingEvent event) {
        logger.info("---attribute added--- " + event.getSession().getAttribute(ParameterName.USER));
        logger.info("---attribute added--- " + event.getSession().getAttribute(ParameterName.PASSWORD));
    }

    @Override
    public void attributeReplaced(HttpSessionBindingEvent event) {
        logger.info("<---attribute replaced---> " + event.getSession().getAttribute(ParameterName.USER));
        logger.info("<---attribute replaced---> " + event.getSession().getAttribute(ParameterName.PASSWORD));
    }

    @Override
    public void attributeRemoved(HttpSessionBindingEvent event) {

    }
}
