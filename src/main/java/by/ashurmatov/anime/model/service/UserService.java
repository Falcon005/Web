package by.ashurmatov.anime.model.service;

import by.ashurmatov.anime.exception.DaoException;

public interface UserService {
    boolean loginAuthenticate(String login,String password);
}
