package by.ashurmatov.anime.model.service;

import by.ashurmatov.anime.exception.DaoException;
import by.ashurmatov.anime.model.entity.User;

public interface UserService extends BaseService<User>{
    boolean loginAuthenticate(String login,String password);

//    boolean add
}
