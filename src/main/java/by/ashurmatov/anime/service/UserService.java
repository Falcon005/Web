package by.ashurmatov.anime.service;

import by.ashurmatov.anime.entity.type.Status;
import by.ashurmatov.anime.entity.type.UserRole;
import by.ashurmatov.anime.exception.DaoException;
import by.ashurmatov.anime.exception.ServiceException;
import by.ashurmatov.anime.entity.User;

import java.util.List;
import java.util.Optional;

public interface UserService extends BaseService<User>{
    boolean loginAuthenticate(String login,String password) throws ServiceException;
    Optional<User> findByLogin(String login) throws ServiceException;
    UserRole findUserRole(String login) throws ServiceException;
    Status findUserStatus(String login) throws ServiceException;
    List<User> findUsersByRole(UserRole userRole) throws ServiceException;
    boolean deleteByLogin(String login) throws ServiceException;
    boolean updateUser(User user) throws ServiceException;
    boolean updatePassword(String login, String newPassword) throws ServiceException;
    boolean isLoginAvailable(String login) throws ServiceException;
    boolean isEmailAvailable(String email) throws ServiceException;
    boolean deleteSoThatToChangeStatusToBlocked(String login) throws ServiceException;

}
