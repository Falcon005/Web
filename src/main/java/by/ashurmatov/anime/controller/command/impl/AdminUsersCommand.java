package by.ashurmatov.anime.controller.command.impl;

import by.ashurmatov.anime.controller.attribute.ParameterName;
import by.ashurmatov.anime.controller.command.Command;
import by.ashurmatov.anime.controller.command.Router;
import by.ashurmatov.anime.controller.path.PagePath;
import by.ashurmatov.anime.entity.User;
import by.ashurmatov.anime.exception.CommandException;
import by.ashurmatov.anime.exception.ServiceException;
import by.ashurmatov.anime.service.UserService;
import by.ashurmatov.anime.service.impl.UserServiceImpl;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.util.List;

public class AdminUsersCommand implements Command {
    private static final Logger logger = LogManager.getLogger(AdminUsersCommand.class);
    @Override
    public Router execute(HttpServletRequest request, HttpServletResponse response) throws CommandException{
        UserService userService = UserServiceImpl.getInstance();
        HttpSession session = request.getSession();//todo with session in some places. Now I'm not using  this. Please use session
        Router router;
        try {
            List<User> users = userService.findAll();
            request.setAttribute(ParameterName.USERS,users);
            router = new Router(PagePath.USERS_PAGE,Router.Type.FORWARD);
            return router;
        }catch (ServiceException serviceException) {
            logger.error("Error in getting all the Users from Database " + serviceException);
            throw new CommandException(serviceException);
        }


    }
}
