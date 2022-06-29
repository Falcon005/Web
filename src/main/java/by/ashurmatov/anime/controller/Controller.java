package by.ashurmatov.anime.controller;

import by.ashurmatov.anime.controller.attribute.ParameterName;
import by.ashurmatov.anime.controller.command.Command;
import by.ashurmatov.anime.controller.command.CommandType;
import by.ashurmatov.anime.controller.command.Router;
import by.ashurmatov.anime.exception.CommandException;
import jakarta.servlet.*;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.*;
import org.apache.logging.log4j.Level;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.io.IOException;
@WebServlet(name = "Controller",urlPatterns = {"/controller.do"})
public class Controller extends HttpServlet {
    private static final Logger logger = LogManager.getLogger(Controller.class);

    @Override
    public void init() throws ServletException {

    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        processRequest(request,response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        processRequest(request,response);
    }
    private void processRequest(HttpServletRequest request,HttpServletResponse response) throws ServletException,IOException{

        Router router;
        String commandStr = request.getParameter(ParameterName.COMMAND);
        logger.log(Level.INFO,"Command is " + commandStr);
        Command command = CommandType.defineCommand(commandStr);

        try {
            router = command.execute(request);
            logger.log(Level.INFO,"moving to " + router.getPage());
            if(router.getActionType() == Router.Type.FORWARD) {
                request.getRequestDispatcher(router.getPage()).forward(request,response);
            }else {
                response.sendRedirect(request.getContextPath() + router.getPage());
            }
        } catch (CommandException e) {
            logger.error("Error in executing command " + commandStr,e);
            throw new ServletException(e);
        }

    }

    @Override
    public void destroy() {
//        DynamicConnectionPool.getInstance().destroyPool();
    }
}
