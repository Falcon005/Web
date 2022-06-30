package by.ashurmatov.anime.controller.command.impl;

import by.ashurmatov.anime.controller.attribute.SessionAttributeName;
import by.ashurmatov.anime.controller.command.Command;
import by.ashurmatov.anime.controller.command.Router;
import by.ashurmatov.anime.controller.path.PagePath;
import by.ashurmatov.anime.controller.attribute.ParameterName;
import by.ashurmatov.anime.entity.User;
import by.ashurmatov.anime.entity.type.Status;
import by.ashurmatov.anime.entity.type.UserRole;
import by.ashurmatov.anime.exception.CommandException;
import by.ashurmatov.anime.exception.ServiceException;
import by.ashurmatov.anime.service.UserService;
import by.ashurmatov.anime.service.impl.UserServiceImpl;
import by.ashurmatov.anime.validator.UserValidator;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpSession;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.util.Optional;


public class LoginCommand implements Command {
    private static final Logger logger = LogManager.getLogger(LoginCommand.class);
    private static final String ERROR_MESSAGE = "incorrect login or password";
    private static final String ERROR_MESSAGE_FOR_BLOCK_USER = "Sorry, you are blocked. Please Go to the registration page";
    @Override
    public Router execute(HttpServletRequest request) throws CommandException {
        boolean isMatched;
        String userName = request.getParameter(ParameterName.USERNAME);
        String password = request.getParameter(ParameterName.PASSWORD);
        HttpSession session = request.getSession();
        Router router;
        UserService userService = UserServiceImpl.getInstance();

        try {
            if(UserValidator.isLoginValid(userName) && UserValidator.isPasswordValid(password)) {

                isMatched = userService.loginAuthenticate(userName,password);

                if (isMatched) {
                    UserRole userRole = userService.findUserRole(userName);
                    Status userStatus = userService.findUserStatus(userName);
                    Optional<User> optionalUser = userService.findByLogin(userName);
                    optionalUser.ifPresent(user -> session.setAttribute(SessionAttributeName.USER,user));
                    request.setAttribute(ParameterName.USERNAME,userName);
                    session.setAttribute(SessionAttributeName.USERNAME,userName);
//                    session.setAttribute(SessionAttributeName.PASSWORD,password); DEPRECATED
                    session.setAttribute(SessionAttributeName.USER_ROLE,userRole);
                    session.setAttribute(SessionAttributeName.USER_STATUS,userStatus);

                    if (session.getAttribute(SessionAttributeName.USER_ROLE) == UserRole.ADMIN) {
                        router = new Router(PagePath.ADMIN_PAGE,Router.Type.FORWARD);
                    } else {
                        if (session.getAttribute(SessionAttributeName.USER_STATUS) == Status.BLOCKED) {
                            request.setAttribute(ParameterName.ERROR_IN_BLOCKING,ERROR_MESSAGE_FOR_BLOCK_USER);
                            router = new Router(PagePath.LOGIN_PAGE,Router.Type.FORWARD);
                        } else {
                            router = new Router(PagePath.HOME_PAGE,Router.Type.FORWARD);
                        }
                    }
                }else {
                    request.setAttribute(ParameterName.ERROR_MESSAGE_LOGIN,ERROR_MESSAGE);
                    router = new Router(PagePath.LOGIN_PAGE,Router.Type.FORWARD);
                }
            }else {
                request.setAttribute(ParameterName.ERROR_MESSAGE_LOGIN,ERROR_MESSAGE);
                router = new Router(PagePath.LOGIN_PAGE,Router.Type.FORWARD);
            }
            return router;
        }catch (ServiceException serviceException) {
            logger.info("Error in Login " + serviceException);
            throw new CommandException(serviceException);
        }
    }
}
