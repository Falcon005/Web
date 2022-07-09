package by.ashurmatov.anime.controller.command.impl;

import by.ashurmatov.anime.controller.command.Command;
import by.ashurmatov.anime.controller.command.Router;
import by.ashurmatov.anime.controller.path.PagePath;
import by.ashurmatov.anime.controller.attribute.ParameterName;
import by.ashurmatov.anime.entity.type.Status;
import by.ashurmatov.anime.exception.CommandException;
import by.ashurmatov.anime.exception.ServiceException;
import by.ashurmatov.anime.entity.User;
import by.ashurmatov.anime.entity.type.UserRole;
import by.ashurmatov.anime.service.UserService;
import by.ashurmatov.anime.service.impl.UserServiceImpl;
import by.ashurmatov.anime.validator.UserValidator;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import org.apache.logging.log4j.Level;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;



public class RegisterCommand implements Command {
    private static final Logger logger= LogManager.getLogger(RegisterCommand.class);

    private static final String ALREADY_EXISTING_LOGIN = " - already existing login";
    private static final String ALREADY_EXISTING_EMAIL = " - already existing email";
    private static final String ERROR_IN_FILLING_FIELD = "Error in filling field";
    private static final String ERROR_FOR_VALIDATION_IN_FIELDS = "Error for validation for fields";
    private final String ADMIN_LOGIN = "adminA1";
    @Override
    public Router execute(HttpServletRequest request, HttpServletResponse response) throws CommandException {

        UserService userService=UserServiceImpl.getInstance();
        String email=request.getParameter(ParameterName.EMAIL);
        String firstname=request.getParameter(ParameterName.FIRSTNAME);
        String lastname = request.getParameter(ParameterName.LASTNAME);
        String username=request.getParameter(ParameterName.USERNAME);
        String password = request.getParameter(ParameterName.PASSWORD);

        logger.log(Level.INFO,"Email of user is " + email);
        logger.log(Level.INFO,"Firstname of user is " + firstname);
        logger.log(Level.INFO,"Lastname of user is " + lastname);
        logger.log(Level.INFO,"Username of user is " + username);
        logger.log(Level.INFO,"Password of user is " + password);



        String errorMessage = UserValidator.validateUserForRegister(email,firstname,lastname,username,password);
        if(!errorMessage.isEmpty()) {
            request.setAttribute(ParameterName.ERROR_IN_VALIDATION,ERROR_FOR_VALIDATION_IN_FIELDS);
            return new Router(PagePath.REGISTER_PAGE,Router.Type.REDIRECT);
        } else{

            User user = new User();
            try{
                if (userService.isLoginAvailable(username)) {
                    user.setUserName(username);
                }else {
                    request.setAttribute(ParameterName.UNAVAILABLE_LOGIN, username + ALREADY_EXISTING_LOGIN);
                    logger.info("ALREADY EXISTING LOGIN");
                    return new Router(PagePath.REGISTER_PAGE,Router.Type.FORWARD);
                }
                if (userService.isEmailAvailable(email)) {
                    user.setEmail(email);
                }else{
                    request.setAttribute(ParameterName.UNAVAILABLE_EMAIL_ADDRESS, email + ALREADY_EXISTING_EMAIL);
                    logger.info("ALREADY EXISTING EMAIL");
                    return new Router(PagePath.REGISTER_PAGE,Router.Type.FORWARD);
                }
                if (username.equals(ADMIN_LOGIN)) {
                    user.setRole(UserRole.ADMIN);
                } else {
                    user.setRole(UserRole.USER);
                }

                user.setFirstname(firstname);
                user.setLastname(lastname);
                user.setPassword(password);
                user.setStatus(Status.ACTIVE);
                request.setAttribute(ParameterName.USER,user);
                if(userService.register(user)) {
                    if (username.equals(ADMIN_LOGIN)) {
                        return new Router(PagePath.ADMIN_PAGE, Router.Type.FORWARD);
                    }
                    return new Router(PagePath.HOME_PAGE,Router.Type.FORWARD);
                }else {
                    request.setAttribute(ParameterName.ERROR_IN_VALIDATION,ERROR_IN_FILLING_FIELD);
                    return new Router(PagePath.REGISTER_PAGE,Router.Type.FORWARD);
                }

            }catch (ServiceException e){
                logger.error("error in registering a new user", e);
                throw new CommandException(e);
            }


        }
    }
}
