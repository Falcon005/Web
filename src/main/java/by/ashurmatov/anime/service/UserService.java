package by.ashurmatov.anime.service;

import by.ashurmatov.anime.exception.ServiceException;
import by.ashurmatov.anime.entity.User;

public interface UserService extends BaseService<User>{
    boolean loginAuthenticate(String login,String password) throws ServiceException;
}
