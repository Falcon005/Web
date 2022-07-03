package by.ashurmatov.anime.service.impl;

import by.ashurmatov.anime.entity.type.Status;
import by.ashurmatov.anime.exception.DaoException;
import by.ashurmatov.anime.exception.ServiceException;
import by.ashurmatov.anime.dao.impl.UserDaoImpl;
import by.ashurmatov.anime.entity.User;
import by.ashurmatov.anime.entity.type.UserRole;
import by.ashurmatov.anime.service.UserService;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.util.List;
import java.util.Optional;


public class UserServiceImpl implements UserService {
    private static final Logger logger = LogManager.getLogger(UserServiceImpl.class);
    private static UserDaoImpl userDao=UserDaoImpl.getInstance();
    private static UserServiceImpl instance = new UserServiceImpl();
    private UserServiceImpl(){

    }

    public static UserServiceImpl getInstance() {
        return instance;
    }

    @Override
    public boolean loginAuthenticate(String login, String password) throws ServiceException{
        try {
            return userDao.authenticate(login,password);
        } catch (DaoException e) {
            logger.error("Error in Authentication " + e);
            throw new ServiceException(e);
        }
    }


    @Override
    public boolean register(User user) throws ServiceException{
        boolean isSaved = false;
        try {
            isSaved = userDao.insert(user);
        } catch (DaoException e) {
            logger.error("Error in Registration " + e);
            throw new ServiceException(e);
        }
        return isSaved;
    }

    @Override
    public boolean delete(Long id) throws ServiceException{
        try {
            return userDao.delete(id);
        } catch (DaoException e) {
            logger.error("Error in deleting User By Id " + e);
            throw new ServiceException(e);
        }

    }


    @Override
    public Optional<User> findById(Long id) throws ServiceException{
        try {
            return userDao.findById(id);
        }catch (DaoException e) {
            logger.error("Error in Finding User By Id " + e);
            throw new ServiceException(e);
        }

    }

    @Override
    public Optional<User> findByLogin(String login) throws ServiceException {
        try {
            return userDao.findByLogin(login);
        }catch (DaoException e) {
            logger.error("Error in finding User By Login " + e);
            throw new ServiceException(e);
        }
    }

    @Override
    public UserRole findUserRole(String login) throws ServiceException {
        try {
            return userDao.findUserRole(login);
        }catch (DaoException e) {
            logger.error("Error in find User Role By Login " + e);
            throw new ServiceException(e);
        }
    }

    @Override
    public Status findUserStatus(String login) throws ServiceException {
        try {
            return userDao.findUserStatus(login);
        }catch (DaoException e) {
            logger.error("Error in find User Role By Login");
            throw new ServiceException(e);
        }
    }

    @Override
    public List<User> findUsersByRole(UserRole userRole) throws ServiceException {
        try {
            return userDao.findUsersByRole(userRole);
        }catch (DaoException e) {
            logger.error("Error in finding Users By UserRole " + e);
            throw new ServiceException(e);
        }
    }

    @Override
    public boolean deleteByLogin(String login) throws ServiceException {
        try {
            return userDao.deleteByLogin(login);
        }catch (DaoException e) {
            logger.error("Error in deleting User By Login " + e);
            throw new ServiceException(e);
        }
    }
    @Override
    public boolean deleteSoThatToChangeStatusToBlocked(String login) throws ServiceException {
        try {
            return userDao.deleteSoThatToChangeStatusToBlocked(login);
        }catch (DaoException e) {
            logger.error("Error in change status to BLOCKED " + e);
            throw new ServiceException(e);
        }
    }

    @Override
    public boolean activateSoThatToChangeStatusToActivated(String login) throws ServiceException {
        try {
            return userDao.activateSoThatToChangeStatusToActivated(login);
        }catch (DaoException e) {
            logger.error("Error in change status to ACTIVATED");
            throw new ServiceException(e);
        }
    }

    @Override
    public boolean updateUser(User user) throws ServiceException {
        try {
            return userDao.updateUser(user);
        }catch (DaoException e) {
            logger.error("Error in updating User " + e);
            throw new ServiceException(e);
        }
    }

    @Override
    public boolean updatePassword(String login, String newPassword) throws ServiceException {
        try {
            return userDao.updatePassword(login,newPassword);
        }catch (DaoException e) {
            logger.error("Error in updating password " + e);
            throw new ServiceException(e);
        }
    }

    @Override
    public boolean isLoginAvailable(String login) throws ServiceException {
        try {
            return userDao.isLoginAvailable(login);
        }catch (DaoException daoException) {
            logger.error("error in finding out whether login is available or not", daoException);
            throw new ServiceException(daoException);
        }
    }

    @Override
    public boolean isEmailAvailable(String email) throws ServiceException {
        try {
            return userDao.isEmailAvailable(email);
        } catch (DaoException daoException) {
            logger.error("error in finding out whether email is available or not", daoException);
            throw new ServiceException(daoException);
        }
    }

    @Override
    public List<User> findAll() throws ServiceException{
        try {
            return userDao.findAll();
        }catch (DaoException e) {
            logger.error("Error in finding Users " + e);
            throw new ServiceException(e);
        }

    }
}
