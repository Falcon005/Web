package by.ashurmatov.anime.controller;

import by.ashurmatov.anime.controller.command.Command;
import by.ashurmatov.anime.controller.command.CommandType;
import by.ashurmatov.anime.exception.DaoException;
import jakarta.servlet.*;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.*;

import java.io.IOException;
@WebServlet(name = "Controller",urlPatterns = {"/controller.do"})
public class Controller extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        processRequest(request,response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        processRequest(request,response);
    }
    private void processRequest(HttpServletRequest request,HttpServletResponse response) throws ServletException,IOException{
        response.setContentType("text/html");
        String commandStr = request.getParameter("command");
        Command command = CommandType.defineCommand(commandStr);
        String page = null;
        try {
            page = command.execute(request);
        } catch (DaoException e) {
            e.printStackTrace();
        }
        request.getRequestDispatcher(page).forward(request,response);
    }
}
