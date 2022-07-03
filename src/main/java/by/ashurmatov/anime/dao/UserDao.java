package by.ashurmatov.anime.dao;

import by.ashurmatov.anime.entity.type.Status;
import by.ashurmatov.anime.entity.type.UserRole;
import by.ashurmatov.anime.exception.DaoException;
import by.ashurmatov.anime.entity.User;

import java.util.List;
import java.util.Optional;

public interface UserDao extends BaseDao<User>{
    boolean authenticate(String login,String password) throws DaoException;
    Optional<User> findByLogin(String login) throws DaoException;
    UserRole findUserRole(String login) throws DaoException;
    Status findUserStatus(String login) throws DaoException;
    List<User> findUsersByRole(UserRole userRole) throws DaoException;
    boolean deleteByLogin(String login) throws DaoException;
    boolean updateUser(User user) throws DaoException;
    boolean updatePassword(String login, String newPassword) throws DaoException;
    boolean isLoginAvailable(String login) throws DaoException;
    boolean isEmailAvailable(String email) throws DaoException;
    boolean deleteSoThatToChangeStatusToBlocked(String login) throws DaoException;
    boolean activateSoThatToChangeStatusToActivated(String login) throws DaoException;
}
