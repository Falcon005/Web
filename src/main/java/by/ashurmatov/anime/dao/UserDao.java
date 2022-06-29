package by.ashurmatov.anime.dao;

import by.ashurmatov.anime.exception.DaoException;
import by.ashurmatov.anime.entity.User;

public interface UserDao extends BaseDao<User>{
    boolean authenticate(String login,String password) throws DaoException;
}
