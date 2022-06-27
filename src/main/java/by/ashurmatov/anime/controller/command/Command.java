package by.ashurmatov.anime.controller.command;

import by.ashurmatov.anime.exception.CommandException;
import by.ashurmatov.anime.exception.DaoException;
import jakarta.servlet.http.HttpServletRequest;

@FunctionalInterface
public interface Command {
    Router execute(HttpServletRequest request) throws CommandException;
}
