package by.ashurmatov.anime.controller.command.impl;

import by.ashurmatov.anime.controller.command.Command;
import by.ashurmatov.anime.controller.command.PagePath;
import by.ashurmatov.anime.controller.command.ParameterName;
import by.ashurmatov.anime.exception.DaoException;
import by.ashurmatov.anime.model.service.UserService;
import by.ashurmatov.anime.model.service.impl.UserServiceImpl;
import by.ashurmatov.anime.model.validator.UserValidator;
import jakarta.servlet.http.HttpServletRequest;

public class LoginCommand implements Command {

    @Override
    public String execute(HttpServletRequest request){
        String login = request.getParameter(ParameterName.USERNAME);
        String password = request.getParameter(ParameterName.PASSWORD);
//        String page;
//        if(UserValidator.isLoginValid(login) && UserValidator.isPasswordValid(password)) {
//            UserService userService = UserServiceImpl.getInstance();
//            boolean isMatched = userService.loginAuthenticate(login,password);
//            if(isMatched){
//                request.setAttribute("user",login);
//                page = PagePath.HOME;
//            }
//            else {
//                request.setAttribute("ErrorInLoginOrPassword", "Invalid Login or Password");
//                page = PagePath.LOGIN;
//            }
//
//        }else {
//            request.setAttribute("ErrorInLoginOrPassword", "Invalid Login or Password");
//            page = PagePath.LOGIN;
//        }

        UserService userService = UserServiceImpl.getInstance();
        String page;
        boolean isMatched = userService.loginAuthenticate(login,password);
        if(isMatched){
            request.setAttribute("user",login);
            page = PagePath.HOME;
        }
        else{
            request.setAttribute("ErrorInLoginOrPassword","Invalid login or password");
            page = PagePath.LOGIN;
        }

        return page;

    }
}
