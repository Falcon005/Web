package by.ashurmatov.anime.controller.command;

import by.ashurmatov.anime.exception.CommandException;
import by.ashurmatov.anime.exception.DaoException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

@FunctionalInterface
public interface Command {
    Router execute(HttpServletRequest request, HttpServletResponse response) throws CommandException;
}
