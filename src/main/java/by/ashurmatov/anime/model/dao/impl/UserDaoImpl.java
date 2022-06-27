package by.ashurmatov.anime.model.dao.impl;


import by.ashurmatov.anime.exception.DaoException;
import by.ashurmatov.anime.exception.ServiceException;
import by.ashurmatov.anime.model.dao.UserDao;
import by.ashurmatov.anime.model.dao.query.UserQuery;
import by.ashurmatov.anime.model.entity.User;
import by.ashurmatov.anime.model.entity.type.UserRole;
import by.ashurmatov.anime.model.pool.DynamicConnectionPool;
import by.ashurmatov.anime.model.util.DaoUtil;
import by.ashurmatov.anime.model.validator.UserValidator;
import org.apache.logging.log4j.Level;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.sql.*;
import java.util.List;
import java.util.Optional;

public class UserDaoImpl implements UserDao {
    private static final String CHECK_LOGIN = "SELECT firstname from users where username=?";
    private static final String CHECK_EMAIL = "SELECT firstname from users where email=?";
    private static final Logger logger=LogManager.getLogger(UserDaoImpl.class);
    private static final UserDaoImpl instance = new UserDaoImpl();

    public static UserDaoImpl getInstance() {
        return instance;
    }

    private UserDaoImpl(){

    }
    @Override
    public boolean insert(User user) throws DaoException{
        Connection connection=null;
        PreparedStatement statement=null;
        int userNumber;

        logger.info(user);
        try{
            connection = DynamicConnectionPool.getInstance().provideConnection();
            statement = connection.prepareStatement(UserQuery.INSERT_USER_QUERY);
            statement.setString(1, user.getEmail());
            statement.setString(2, UserRole.USER.toString());
            statement.setString(3,user.getFirstname());
            statement.setString(4,user.getLastname());
            statement.setString(5, user.getUserName());
            statement.setString(6, user.getPassword());
            userNumber = statement.executeUpdate();

            logger.info("User row: "+userNumber);
            return userNumber > 0;
        } catch (SQLException e) {
            throw new DaoException("Error in saving user " + UserQuery.INSERT_USER_QUERY,e);
        }finally {
            DaoUtil.releaseResources(connection,statement);
        }

    }

    @Override
    public boolean delete(User user) {
        return false;
    }

    @Override
    public List<User> findAll() {
        return null;
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
    public Optional<User> findByLogin(String login) throws DaoException {
        return Optional.empty();
    }

    @Override
    public UserRole findUserRole(String login) throws DaoException {
        return null;
    }

    @Override
    public List<User> findUsersByRole(UserRole userRole) throws DaoException {
        return null;
    }

    @Override
    public boolean deleteByLogin(String login) throws DaoException {
        return false;
    }

    @Override
    public boolean updateUser(User user) throws DaoException {
        return false;
    }

    @Override
    public boolean updatePassword(String login, String newPassword) throws DaoException {
        return false;
    }

    @Override
    public boolean isLoginAvailable(String login) throws DaoException {
        try(Connection connection = DynamicConnectionPool.getInstance().provideConnection();
            PreparedStatement preparedStatement = connection.prepareStatement(CHECK_LOGIN)){
            preparedStatement.setString(1,login);
            try(ResultSet resultSet = preparedStatement.executeQuery()) {
                boolean toReturn  = !resultSet.next();
                logger.log(Level.INFO,"dao level : " + login + " 's availability is " + toReturn);
                return toReturn;
            }catch (SQLException sqlException) {
                logger.error("error in connecting the database", sqlException);
                throw new DaoException(sqlException);
            }
        } catch (SQLException sqlException) {
            logger.error("error in connecting the database", sqlException);
            throw new DaoException(sqlException);
        }
    }

    @Override
    public boolean isEmailAvailable(String email) throws DaoException {
        try(Connection connection = DynamicConnectionPool.getInstance().provideConnection();
            PreparedStatement preparedStatement = connection.prepareStatement(CHECK_EMAIL)){
            preparedStatement.setString(1,email);
            try(ResultSet resultSet = preparedStatement.executeQuery()) {
                boolean toReturn  = !resultSet.next();
                logger.log(Level.INFO,"dao level : " + email + " 's availability is " + toReturn);
                return toReturn;
            }catch (SQLException sqlException) {
                logger.error("error in connecting the database", sqlException);
                throw new DaoException(sqlException);
            }
        } catch (SQLException sqlException) {
            logger.error("error in connecting the database", sqlException);
            throw new DaoException(sqlException);
        }
    }

    @Override
    public boolean authenticate(String login, String password) throws DaoException{
        if (login.isEmpty() || password.isEmpty()) {
            return false;
        }
        try(Connection connection = DynamicConnectionPool.getInstance().provideConnection();
            PreparedStatement preparedStatement = connection.prepareStatement(UserQuery.CHECK_FOR_LOGIN)){
            preparedStatement.setString(1,login);
            try(ResultSet resultSet = preparedStatement.executeQuery()) {
                String passwordFromDb;
                if(resultSet.next()) {
                    passwordFromDb = resultSet.getString("password");
                    return UserValidator.isPasswordMatches(password,passwordFromDb);
                }
            }
        }catch (SQLException exception) {
            logger.error("error in connecting the database", exception);
            throw new DaoException(exception);
        }
        return false;
    }
}
