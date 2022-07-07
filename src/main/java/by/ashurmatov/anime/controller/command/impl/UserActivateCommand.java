package by.ashurmatov.anime.controller.command.impl;

import by.ashurmatov.anime.controller.attribute.ParameterName;
import by.ashurmatov.anime.controller.command.Command;
import by.ashurmatov.anime.controller.command.Router;
import by.ashurmatov.anime.entity.User;
import by.ashurmatov.anime.exception.CommandException;
import by.ashurmatov.anime.exception.ServiceException;
import by.ashurmatov.anime.service.UserService;
import by.ashurmatov.anime.service.impl.UserServiceImpl;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.util.Optional;

public class UserActivateCommand implements Command {
    private static final Logger logger = LogManager.getLogger(UserActivateCommand.class);
    @Override
    public Router execute(HttpServletRequest request, HttpServletResponse response) throws CommandException{
        Router router = null;
        UserService userService = UserServiceImpl.getInstance();
        String username = request.getParameter("userName");
        logger.info("Username is " + username);
        try {
            Optional<User> optionalUser = userService.findByLogin(username);
            String userToString = "";
            if (optionalUser.isPresent()) {
                userToString = optionalUser.get().toString();
            }
            if (userService.activateSoThatToChangeStatusToActivated(username)) {
                logger.info("User which username is "+username+" successfully activated ");
                router = new AdminUsersCommand().execute(request,response);
                logger.info("Router is " + router + " in If ");
                request.setAttribute(ParameterName.USER_ACTIVATED,userToString);

            }else {
                logger.info("User which username is "+username+" not blocked ");
                request.setAttribute(ParameterName.USER_NOT_ACTIVATED,ParameterName.USER_NOT_ACTIVATED);
            }
        }catch (ServiceException serviceException) {
            logger.error("Error in activating User " + username + serviceException);
            throw new CommandException(serviceException);
        }
        logger.info("Router is " + router);
        return router;

    }
}
