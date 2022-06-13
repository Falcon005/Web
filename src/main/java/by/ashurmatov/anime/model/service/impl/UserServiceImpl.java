package by.ashurmatov.anime.model.service.impl;

import by.ashurmatov.anime.model.service.UserService;

public class UserServiceImpl implements UserService {
    private static UserServiceImpl instance = new UserServiceImpl();
    private UserServiceImpl(){

    }

    public static UserServiceImpl getInstance() {
        return instance;
    }

    @Override
    public boolean loginAuthenticate(String login, String password) {
        return login.equals(password);
    }
}
