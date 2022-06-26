package by.ashurmatov.anime.controller.command.impl;

import by.ashurmatov.anime.controller.command.Command;
import by.ashurmatov.anime.controller.path.PagePath;
import by.ashurmatov.anime.controller.attribute.RequestParameterName;
import by.ashurmatov.anime.exception.CommandException;
import by.ashurmatov.anime.exception.ServiceException;
import by.ashurmatov.anime.model.service.UserService;
import by.ashurmatov.anime.model.service.impl.UserServiceImpl;
import by.ashurmatov.anime.model.validator.UserValidator;
import jakarta.servlet.http.HttpServletRequest;

public class LoginCommand implements Command {

    @Override
    public String execute(HttpServletRequest request) throws CommandException {
        String login = request.getParameter(RequestParameterName.USERNAME);
        String password = request.getParameter(RequestParameterName.PASSWORD);
        UserService userService = UserServiceImpl.getInstance();
        String page;
        boolean isMatched;
//        boolean isMatched = false;
//        try {
//            isMatched = userService.loginAuthenticate(login,password);
//        } catch (ServiceException e) {
//            throw new CommandException(e);
//        }
//        if(isMatched){
//            request.setAttribute("user",login);
//            page = PagePath.HOME;
//        }
//        else{
//            request.setAttribute("ErrorInLoginOrPassword","Invalid login or password");
//            page = PagePath.LOGIN;
//        }
//
//        return page;

        //        String page;
        if(UserValidator.isLoginValid(login) && UserValidator.isPasswordValid(password)) {
            try {
                isMatched = userService.loginAuthenticate(login,password);
            } catch (ServiceException e) {
                throw new CommandException(e);
            }
            if(isMatched){
                request.setAttribute("user",login);
                page = PagePath.HOME;
            }
            else {
                request.setAttribute("ErrorInLoginOrPassword", "Invalid Login or Password");
                page = PagePath.LOGIN;
            }

        }else {
            request.setAttribute("ErrorInLoginOrPassword", "Invalid Login or Password");
            page = PagePath.LOGIN;
        }
        return page;
    }
}
