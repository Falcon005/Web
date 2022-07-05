package by.ashurmatov.anime.controller.command.impl;


import by.ashurmatov.anime.controller.command.Command;
import by.ashurmatov.anime.controller.command.Router;
import by.ashurmatov.anime.controller.path.PagePath;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpSession;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class LogoutCommand implements Command {
    private static final Logger logger = LogManager.getLogger(LogoutCommand.class);
    @Override
    public Router execute(HttpServletRequest request) {
        HttpSession session = request.getSession();
        if (!session.isNew()) {
            session.invalidate();
            logger.info("Session is destroyed in Logout Command");
        }
        return new Router(PagePath.INDEX_PAGE,Router.Type.REDIRECT);

    }
}
