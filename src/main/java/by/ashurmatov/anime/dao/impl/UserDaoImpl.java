package by.ashurmatov.anime.dao.impl;


import by.ashurmatov.anime.dao.UserDao;
import by.ashurmatov.anime.dao.mapper.ColumnName;
import by.ashurmatov.anime.dao.mapper.impl.UserMapper;
import by.ashurmatov.anime.dao.query.UserQuery;
import by.ashurmatov.anime.exception.DaoException;
import by.ashurmatov.anime.entity.User;
import by.ashurmatov.anime.entity.type.UserRole;
import by.ashurmatov.anime.pool.DynamicConnectionPool;
import by.ashurmatov.anime.util.DaoUtil;
import by.ashurmatov.anime.validator.UserValidator;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class UserDaoImpl implements UserDao {
    private final UserMapper userMapper = new UserMapper();
    private static final String CHECK_LOGIN = "SELECT users.firstname FROM users WHERE users.username=?";
    private static final String CHECK_EMAIL = "SELECT users.firstname FROM users WHERE users.email=?";
    private static final String DELETE_USER_BY_ID = "DELETE FROM users WHERE userid = ?";
    private static final String SELECT_ALL_USERS = "SELECT userid,email,role,firstname,lastname,username,password FROM users";
    private static final String SELECT_BY_LOGIN =  "SELECT userid,email,role,firstname,lastname,username,password FROM users WHERE username = ?";
    private static final String FIND_USER_ROLE_BY_LOGIN = "SELECT role FROM users WHERE username = ?";
    private static final String FIND_BY_ID = "SELECT userid,email,role,firstname,lastname,username,password FROM users WHERE userid = ?";
    private static final String FIND_BY_ROLE = "SELECT userid,email,role,firstname,lastname,username,password FROM users WHERE role = ?";
    private static final String UPDATE_USER = "UPDATE users SET email = ?,firstname = ?, lastname = ?,username = ?, password = ? WHERE userid = ?";
    private static final String UPDATE_PASSWORD = "UPDATE users SET password = ? WHERE username = ?";
    private static final String DELETE_BY_LOGIN = "DELETE FROM users WHERE username = ?";
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
            statement.setString(2, user.getRole().toString());
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
    public boolean delete(Long Id) throws DaoException{
        try (Connection connection = DynamicConnectionPool.getInstance().provideConnection();
            PreparedStatement statement = connection.prepareStatement(DELETE_USER_BY_ID)) {
            statement.setLong(1, Id);
            int count = statement.executeUpdate();
            return count == 1;
        }catch (SQLException sqlException) {
            logger.error("Error in deleting the User By Id " + sqlException);
            throw new DaoException(sqlException);
        }

    }

    @Override
    public List<User> findAll() throws DaoException{
        try (Connection connection = DynamicConnectionPool.getInstance().provideConnection();
            PreparedStatement statement = connection.prepareStatement(SELECT_ALL_USERS)) {
            try (ResultSet resultSet = statement.executeQuery()) {
                List<User> users = new ArrayList<>();
                while (resultSet.next()) {
                    Optional<User> optionalUser = userMapper.map(resultSet);
                    optionalUser.ifPresent(users::add);
                }
                return users;
            }
        }catch (SQLException sqlException) {
            logger.error("Error in connecting database",sqlException);
            throw new DaoException(sqlException);
        }
    }



    @Override
    public Optional<User> findById(Long id) throws DaoException{
        try (Connection connection = DynamicConnectionPool.getInstance().provideConnection();
            PreparedStatement statement = connection.prepareStatement(FIND_BY_ID)) {
            statement.setLong(1, id);
            try (ResultSet resultSet = statement.executeQuery()) {
                Optional<User> user;
                if (resultSet.next()) {
                    user = userMapper.map(resultSet);
                    return user;
                }
            }
        }catch (SQLException sqlException) {
            logger.error("Error in finding User By ID in database " + sqlException);
            throw new DaoException(sqlException);
        }
        return Optional.empty();
    }

    @Override
    public Optional<User> findByLogin(String login) throws DaoException {
        try (Connection connection = DynamicConnectionPool.getInstance().provideConnection();
            PreparedStatement statement = connection.prepareStatement(SELECT_BY_LOGIN)) {
            statement.setString(1,login);
            try (ResultSet resultSet = statement.executeQuery()) {
                Optional<User> user;
                if (resultSet.next()) {
                    user = userMapper.map(resultSet);
                    return user;
                }
            }
        }catch (SQLException sqlException) {
            logger.error("Error in connecting database ",sqlException);
            throw new DaoException(sqlException);
        }
        return Optional.empty();
    }

    @Override
    public UserRole findUserRole(String login) throws DaoException {
        UserRole userRole = null;
        try(Connection connection = DynamicConnectionPool.getInstance().provideConnection();
            PreparedStatement statement = connection.prepareStatement(FIND_USER_ROLE_BY_LOGIN)) {
            statement.setString(1, login);
            try (ResultSet resultSet = statement.executeQuery()) {
                if (resultSet.next()) {
                    userRole = UserRole.valueOf(resultSet.getString(ColumnName.ROLE).toUpperCase());
                }
            }
        }catch (SQLException sqlException) {
            logger.error("Error in connecting database ",sqlException);
            throw new DaoException(sqlException);
        }
        return userRole;
    }

    @Override
    public List<User> findUsersByRole(UserRole userRole) throws DaoException {
        try (Connection connection = DynamicConnectionPool.getInstance().provideConnection();
            PreparedStatement statement = connection.prepareStatement(FIND_BY_ROLE)) {
            statement.setString(1,userRole.toString().toUpperCase());
            return getUsersByRole(statement);
        }catch (SQLException sqlException) {
            logger.error("Error in finding Users By Role in Database " + sqlException);
            throw new DaoException(sqlException);
        }
    }

    private List<User> getUsersByRole(PreparedStatement statement) throws SQLException {
        List<User> users = new ArrayList<>();
        try (ResultSet resultSet = statement.executeQuery()) {
            while(resultSet.next()) {
                Optional<User> user = userMapper.map(resultSet);
                user.ifPresent(users::add);
            }
        }
        return users;
    }

    @Override
    public boolean deleteByLogin(String login) throws DaoException {
        try (Connection connection = DynamicConnectionPool.getInstance().provideConnection();
            PreparedStatement statement = connection.prepareStatement(DELETE_BY_LOGIN)) {
            statement.setString(1,login);
            int count = statement.executeUpdate();
            return count == 1;
        }catch (SQLException sqlException) {
            logger.error("Error in deleting User by Login in Database " + sqlException);
            throw new DaoException(sqlException);
        }
    }

    @Override
    public boolean updateUser(User user) throws DaoException {
        try (Connection connection = DynamicConnectionPool.getInstance().provideConnection();
            PreparedStatement statement = connection.prepareStatement(UPDATE_USER)) {
            statement.setString(1, user.getEmail());
            statement.setString(2,user.getFirstname());
            statement.setString(3,user.getLastname());
            statement.setString(4,user.getUserName());
            statement.setString(5,user.getPassword());
            statement.setLong(6,user.getId());
            int count = statement.executeUpdate();
            return count == 1;
        }catch (SQLException sqlException) {
            logger.error("Error in updating Use By Id in Database " + sqlException);
            throw new DaoException(sqlException);
        }
    }

    @Override
    public boolean updatePassword(String login, String newPassword) throws DaoException {
        try (Connection connection = DynamicConnectionPool.getInstance().provideConnection();
            PreparedStatement statement = connection.prepareStatement(UPDATE_PASSWORD)) {
            statement.setString(1,newPassword);
            statement.setString(2,login);
            int result = statement.executeUpdate();
            return result == 1;
        }catch (SQLException sqlException) {
            logger.error("Error in updating User password In Database " + sqlException);
            throw new DaoException(sqlException);
        }
    }

    @Override
    public boolean isLoginAvailable(String login) throws DaoException {
        return EmailAndLoginChecker(login,CHECK_LOGIN);
//        try(Connection connection = DynamicConnectionPool.getInstance().provideConnection();
//            PreparedStatement preparedStatement = connection.prepareStatement(CHECK_LOGIN)){
//            preparedStatement.setString(1,login);
//            try(ResultSet resultSet = preparedStatement.executeQuery()) {
////                boolean toReturn  = !resultSet.next();
//                boolean isAvailable = false;
//                if (resultSet.next()) {
//                    isAvailable = true;
//                }
//                logger.log(Level.INFO,"dao level : " + login + " 's availability is " + isAvailable);
//                return !isAvailable;
//            }catch (SQLException sqlException) {
//                logger.error("error in connecting the database", sqlException);
//                throw new DaoException(sqlException);
//            }
//        } catch (SQLException sqlException) {
//            logger.error("error in connecting the database", sqlException);
//            throw new DaoException(sqlException);
//        }

    }

    @Override
    public boolean isEmailAvailable(String email) throws DaoException {
        return EmailAndLoginChecker(email,CHECK_EMAIL);
//        try(Connection connection = DynamicConnectionPool.getInstance().provideConnection();
//            PreparedStatement preparedStatement = connection.prepareStatement(CHECK_EMAIL)){
//            preparedStatement.setString(1,email);
//            try(ResultSet resultSet = preparedStatement.executeQuery()) {
//                boolean isAvailable  = !resultSet.isBeforeFirst();
//                logger.log(Level.INFO,"dao level : " + email + " 's availability is " + isAvailable);
//                return isAvailable;
//            }catch (SQLException sqlException) {
//                logger.error("error in connecting the database", sqlException);
//                throw new DaoException(sqlException);
//            }
//        } catch (SQLException sqlException) {
//            logger.error("error in connecting the database", sqlException);
//            throw new DaoException(sqlException);
//        }
    }
    private boolean EmailAndLoginChecker(String loginOrEmail, String check) throws DaoException{
        try(Connection connection = DynamicConnectionPool.getInstance().provideConnection();
            PreparedStatement preparedStatement = connection.prepareStatement(check)){
            logger.info(loginOrEmail + " is login or email");
            preparedStatement.setString(1,loginOrEmail);
            logger.info(preparedStatement.toString());
            try(ResultSet resultSet = preparedStatement.executeQuery()) {
                boolean toReturn  = !resultSet.next();
                logger.info("dao level : " + loginOrEmail + " is availability is " + toReturn);
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
