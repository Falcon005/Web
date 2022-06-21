package by.ashurmatov.anime.model.service.impl;

import by.ashurmatov.anime.exception.DaoException;
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
    public boolean loginAuthenticate(String login, String password){
        //todo
        //Validate login and password
        UserDaoImpl userDao = UserDaoImpl.getInstance();
        return userDao.authenticate(login,password);
    }


    @Override
    public boolean add(User user) {
        // todo validation
        boolean isSaved = userDao.insert(user);
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
