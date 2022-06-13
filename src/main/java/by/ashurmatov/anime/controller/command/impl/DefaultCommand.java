package by.ashurmatov.anime.controller.command.impl;

import by.ashurmatov.anime.controller.command.Command;
import jakarta.servlet.http.HttpServletRequest;

public class DefaultCommand implements Command {
    @Override
    public String execute(HttpServletRequest request) {
        return "login.jsp";
    }
}
