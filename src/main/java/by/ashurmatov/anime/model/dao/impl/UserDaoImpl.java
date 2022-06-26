package by.ashurmatov.anime.model.dao.impl;


import by.ashurmatov.anime.exception.DaoException;
import by.ashurmatov.anime.model.dao.UserDao;
import by.ashurmatov.anime.model.dao.query.UserQuery;
import by.ashurmatov.anime.model.entity.User;
import by.ashurmatov.anime.model.entity.type.Role;
import by.ashurmatov.anime.model.pool.DynamicConnectionPool;
import by.ashurmatov.anime.model.util.DaoUtil;
import by.ashurmatov.anime.model.validator.UserValidator;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.sql.*;
import java.util.List;
import java.util.Optional;

public class UserDaoImpl implements UserDao {
//    private static final String INSERT_QUERY="INSERT INTO users(email,role,firstname,lastname,username,password) VALUES(?,?,?,?,?,?)";
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
            statement.setString(2, Role.USER.toString());
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
    public User update(User user) {
        return null;
    }

    @Override
    public Optional<User> findById(Long id) {
        return Optional.empty();
    }

    @Override
    public boolean authenticate(String login, String password) throws DaoException{
        Connection connection=null;
        PreparedStatement statement=null;
        ResultSet resultSet=null;
        String passwordFromDb;
        boolean match=false;
        //        try {
//            DriverManager.registerDriver(new org.postgresql.Driver());
//        } catch (SQLException e) {
//            e.printStackTrace();
//        }
//        String url ="jdbc:postgresql://localhost:5432/web";
//        String root ="postgres";
//        String passwordOfDb = "1404";
//        try(Connection connection = DriverManager.getConnection(url,root,passwordOfDb); PreparedStatement statement = connection.prepareStatement(query)){
//            statement.setString(1,login);
//            ResultSet resultSet = statement.executeQuery();
//            while(resultSet.next()){
//                passwordFromDb = resultSet.getString("password");
//                match = passwordFromDb.equals(password);
//            }
//
//        } catch (SQLException e) {
//            e.printStackTrace();
//        }
        try{
            connection = DynamicConnectionPool.getInstance().provideConnection();
            statement = connection.prepareStatement(UserQuery.CHECK_FOR_LOGIN);
            statement.setString(1,login);
            resultSet = statement.executeQuery();
            while(resultSet.next()){
                passwordFromDb = resultSet.getString("password");
//                match = passwordFromDb.equals(password);
                match = UserValidator.isPasswordMatches(passwordFromDb,password);
            }
        }catch (SQLException e){
            throw new DaoException("Error Authenticate Executing query " + UserQuery.CHECK_FOR_LOGIN,e);
        }finally {
            DaoUtil.releaseResources(connection,statement,resultSet);
        }
        return match;
    }
}
