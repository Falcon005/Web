package by.ashurmatov.anime.model.dao.impl;

import by.ashurmatov.anime.exception.DaoException;
import by.ashurmatov.anime.model.dao.BaseDao;
import by.ashurmatov.anime.model.dao.UserDao;
import by.ashurmatov.anime.model.entity.User;
import by.ashurmatov.anime.model.pool.DynamicConnectionPool;
import by.ashurmatov.anime.model.service.impl.UserServiceImpl;
import by.ashurmatov.anime.model.util.DaoUtil;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.sql.*;
import java.util.List;

public class UserDaoImpl extends BaseDao<User> implements UserDao {
    private static Logger logger=LogManager.getLogger(UserDaoImpl.class);
    private static UserDaoImpl instance = new UserDaoImpl();

    public static UserDaoImpl getInstance() {
        return instance;
    }

    private UserDaoImpl(){

    }
    @Override
    public boolean insert(User user) {
        return false;
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
    public boolean authenticate(String login, String password){
//        try {
//            DriverManager.registerDriver(new org.postgresql.Driver());
//        } catch (SQLException e) {
//            e.printStackTrace();
//        }
//        String url ="jdbc:postgresql://localhost:5432/web";
//        String root ="postgres";
//        String passwordOfDb = "1404";
        Connection connection=null;
        PreparedStatement statement=null;
        ResultSet resultSet=null;
        String query = "SELECT password FROM users WHERE username=?";
        String passwordFromDb;
        boolean match=false;
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
            statement = connection.prepareStatement(query);
            statement.setString(1,login);
            resultSet = statement.executeQuery();
            while(resultSet.next()){
                passwordFromDb = resultSet.getString("password");
                match = passwordFromDb.equals(password);
            }
        }catch (SQLException e){
            e.printStackTrace();
        }finally {
            DaoUtil.releaseResources(connection,statement,resultSet);
        }
        return match;
    }
}
