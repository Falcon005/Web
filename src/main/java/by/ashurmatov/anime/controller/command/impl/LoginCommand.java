package by.ashurmatov.anime.controller.command.impl;

import by.ashurmatov.anime.controller.command.Command;
import by.ashurmatov.anime.exception.DaoException;
import by.ashurmatov.anime.model.service.UserService;
import by.ashurmatov.anime.model.service.impl.UserServiceImpl;
import jakarta.servlet.http.HttpServletRequest;

public class LoginCommand implements Command {

    @Override
    public String execute(HttpServletRequest request){
        String login = request.getParameter("login");
        String password = request.getParameter("password");
        UserService userService = UserServiceImpl.getInstance();
        String page;
        boolean isMatched = userService.loginAuthenticate(login,password);
        if(isMatched){
            request.setAttribute("user",login);
            page = "WEB-INF/pages/jsp/main.jsp";
        }
        else{
            request.setAttribute("loginError","Invalid login or password");
            page = "WEB-INF/pages/jsp/login.jsp";
        }
        return page;
    }
}
