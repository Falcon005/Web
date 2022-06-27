package by.ashurmatov.anime.controller.command.impl;

import by.ashurmatov.anime.controller.command.Command;
import by.ashurmatov.anime.controller.command.Router;
import by.ashurmatov.anime.controller.path.PagePath;
import by.ashurmatov.anime.controller.attribute.ParameterName;
import by.ashurmatov.anime.exception.CommandException;
import by.ashurmatov.anime.exception.ServiceException;
import by.ashurmatov.anime.model.service.UserService;
import by.ashurmatov.anime.model.service.impl.UserServiceImpl;
import by.ashurmatov.anime.model.validator.UserValidator;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpSession;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;



public class LoginCommand implements Command {
    private static final Logger logger = LogManager.getLogger(LoginCommand.class);
    private static final String ERROR_MESSAGE = "incorrect login or password";
    @Override
    public Router execute(HttpServletRequest request) throws CommandException {
        boolean isMatched;
        String userName = request.getParameter(ParameterName.USERNAME);
        String password = request.getParameter(ParameterName.PASSWORD);
        HttpSession session = request.getSession();
        Router router;
        UserService userService = UserServiceImpl.getInstance();
        if(UserValidator.isLoginValid(userName) && UserValidator.isPasswordValid(password)) {
            try {
                isMatched = userService.loginAuthenticate(userName,password);
            } catch (ServiceException e) {
                logger.error("Error in Login ", e);
                throw new CommandException(e);
            }
            if (isMatched) {
                request.setAttribute(ParameterName.USERNAME,userName);
                session.setAttribute(ParameterName.USERNAME,userName);
                session.setAttribute(ParameterName.PASSWORD,password);
                router = new Router(request.getContextPath()+PagePath.HOME_PAGE,Router.Type.FORWARD);
            }else {
                request.setAttribute(ParameterName.ERROR_MESSAGE_LOGIN,ERROR_MESSAGE);
                router = new Router(PagePath.LOGIN_PAGE,Router.Type.FORWARD);
            }
        }else {
            request.setAttribute(ParameterName.ERROR_MESSAGE_LOGIN,ERROR_MESSAGE);
            router = new Router(request.getContextPath()+PagePath.LOGIN_PAGE,Router.Type.FORWARD);
        }
        return router;
    }
}
