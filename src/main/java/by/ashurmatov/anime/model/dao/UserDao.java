package by.ashurmatov.anime.model.dao;

import by.ashurmatov.anime.exception.DaoException;
import by.ashurmatov.anime.model.entity.User;

public interface UserDao extends BaseDao<User>{
    boolean authenticate(String login,String password) throws DaoException;
}
