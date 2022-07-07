package by.ashurmatov.anime.controller.command.impl;

import by.ashurmatov.anime.controller.command.Command;
import by.ashurmatov.anime.controller.command.Router;
import by.ashurmatov.anime.controller.path.PagePath;
import by.ashurmatov.anime.exception.CommandException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class DefaultCommand implements Command {
    private static final Logger logger = LogManager.getLogger(DefaultCommand.class);
    @Override
    public Router execute(HttpServletRequest request, HttpServletResponse response) throws CommandException{
        try {
            Router router  = new Router(PagePath.INDEX_PAGE, Router.Type.FORWARD);
            logger.info("Router is " + router + " in Default Command");
            return router;
        }catch (IllegalArgumentException exception) {
            throw new CommandException(exception);
        }

    }
}
