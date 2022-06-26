package by.ashurmatov.anime.controller.command.impl;

import by.ashurmatov.anime.controller.command.Command;
import by.ashurmatov.anime.controller.path.PagePath;
import by.ashurmatov.anime.controller.attribute.RequestParameterName;
import by.ashurmatov.anime.exception.CommandException;
import by.ashurmatov.anime.exception.ServiceException;
import by.ashurmatov.anime.model.entity.User;
import by.ashurmatov.anime.model.service.UserService;
import by.ashurmatov.anime.model.service.impl.UserServiceImpl;
import by.ashurmatov.anime.model.validator.UserValidator;
import jakarta.servlet.http.HttpServletRequest;
import org.apache.logging.log4j.Level;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class RegisterCommand implements Command {
    Logger logger= LogManager.getLogger();
    @Override
    public String execute(HttpServletRequest request) throws CommandException {
        String email=request.getParameter(RequestParameterName.EMAIL);
        String firstname=request.getParameter(RequestParameterName.FIRSTNAME);
        String lastname = request.getParameter(RequestParameterName.LASTNAME);
        String username=request.getParameter(RequestParameterName.USERNAME);
        String password = request.getParameter(RequestParameterName.PASSWORD);
        String page;
        boolean isSaved;
        String errorMessage = UserValidator.validateUserForRegister(email,firstname,lastname,username,password);
        if(!errorMessage.isEmpty()) {
            page = PagePath.REGISTER;
        } else{
            UserService userService=UserServiceImpl.getInstance();

            User user=new User();

            user.setEmail(email);
            user.setFirstname(firstname);
            user.setLastname(lastname);
            user.setUserName(username);
            user.setPassword(password);

            try {
                isSaved = userService.add(user);
            } catch (ServiceException e) {
                throw new CommandException(e);
            }

            if (isSaved){
                logger.log(Level.INFO,"User is saved");
                request.setAttribute("user",user.getUserName());
                page = PagePath.HOME;
            }
            else{
                logger.log(Level.INFO,"User is not saved");
                request.setAttribute("registrationError","Invalid  data");
                page = PagePath.REGISTER;
            }


        }
        return page;

//        UserService userService=UserServiceImpl.getInstance();
//
//        User user=new User();
//
//        user.setEmail(email);
//        user.setFirstname(firstname);
//        user.setLastname(lastname);
//        user.setUserName(username);
//        user.setPassword(password);
//
//
//        logger.info(user);
//        if (userService.add(user)){
//            request.setAttribute("user",user.getUserName());
//            page = PagePath.HOME;
//        }
//        else{
//            request.setAttribute("registrationError","Invalid  data");
//            page = PagePath.REGISTER;
//        }
//
//        return page;

    }
}
