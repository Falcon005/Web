package by.ashurmatov.anime.model.dao;

import by.ashurmatov.anime.exception.DaoException;

public interface UserDao {
    boolean authenticate(String login,String password);

}
