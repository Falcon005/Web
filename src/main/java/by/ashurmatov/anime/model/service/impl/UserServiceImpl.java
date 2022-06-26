package by.ashurmatov.anime.model.service.impl;

import by.ashurmatov.anime.exception.DaoException;
import by.ashurmatov.anime.exception.ServiceException;
import by.ashurmatov.anime.model.dao.impl.UserDaoImpl;
import by.ashurmatov.anime.model.entity.User;
import by.ashurmatov.anime.model.service.UserService;

import java.util.List;
import java.util.Optional;

public class UserServiceImpl implements UserService {
    private static UserDaoImpl userDao=UserDaoImpl.getInstance();
    private static UserServiceImpl instance = new UserServiceImpl();
    private UserServiceImpl(){

    }

    public static UserServiceImpl getInstance() {
        return instance;
    }

    @Override
    public boolean loginAuthenticate(String login, String password) throws ServiceException{
        UserDaoImpl userDao = UserDaoImpl.getInstance();
        try {
            return userDao.authenticate(login,password);
        } catch (DaoException e) {
            throw new ServiceException(e);
        }
    }


    @Override
    public boolean add(User user) throws ServiceException{
        boolean isSaved = false;
        try {
            isSaved = userDao.insert(user);
        } catch (DaoException e) {
            throw new ServiceException(e);
        }
        return isSaved;
    }

    @Override
    public boolean delete(Long id) {
        return false;
    }

    @Override
    public boolean update(User user) {
        return false;
    }

    @Override
    public Optional<User> findById(Long id) {
        return Optional.empty();
    }

    @Override
    public List<User> findAll() {
        return null;
    }
}
