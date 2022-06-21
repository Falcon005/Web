package by.ashurmatov.anime.controller.command.impl;

import by.ashurmatov.anime.controller.command.Command;
import by.ashurmatov.anime.controller.command.PagePath;
import by.ashurmatov.anime.controller.command.ParameterName;
import by.ashurmatov.anime.model.entity.User;
import by.ashurmatov.anime.model.service.UserService;
import by.ashurmatov.anime.model.service.impl.UserServiceImpl;
import by.ashurmatov.anime.model.validator.UserValidator;
import jakarta.servlet.http.HttpServletRequest;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class RegisterCommand implements Command {
    Logger logger= LogManager.getLogger();
    @Override
    public String execute(HttpServletRequest request) {
        String email=request.getParameter(ParameterName.EMAIL);
        String firstname=request.getParameter(ParameterName.FIRSTNAME);
        String lastname = request.getParameter(ParameterName.LASTNAME);
        String username=request.getParameter(ParameterName.USERNAME);
        String password = request.getParameter(ParameterName.PASSWORD);
        String page;

        String errorMessage = UserValidator.validateUserForRegister(email,firstname,lastname,username,password);
        if(!errorMessage.equals("")) {
            page = PagePath.REGISTER;
        } else{
            UserService userService=UserServiceImpl.getInstance();

            User user=new User();

            user.setEmail(email);
            user.setFirstname(firstname);
            user.setLastname(lastname);
            user.setUserName(username);
            user.setPassword(password);


            logger.info(user);
            if (userService.add(user)){
                request.setAttribute("user",user.getUserName());
                page = PagePath.HOME;
            }
            else{
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
