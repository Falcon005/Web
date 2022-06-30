package by.ashurmatov.anime.dao;

import by.ashurmatov.anime.entity.type.Status;
import by.ashurmatov.anime.exception.DaoException;
import by.ashurmatov.anime.entity.AbstractEntity;
import by.ashurmatov.anime.entity.User;
import by.ashurmatov.anime.entity.type.UserRole;

import java.util.List;
import java.util.Optional;

public interface BaseDao<T extends AbstractEntity>{
    boolean insert(T t) throws DaoException;
    boolean delete(Long Id) throws DaoException;
    List<T> findAll() throws DaoException;
    Optional<T> findById(Long id) throws DaoException;

//    Optional<T> findByLogin(String login) throws DaoException;
//    UserRole findUserRole(String login) throws DaoException;
//    Status findUserStatus(String login) throws DaoException;
//    List<T> findUsersByRole(UserRole userRole) throws DaoException;
//    boolean deleteByLogin(String login) throws DaoException;
//    boolean updateUser(User user) throws DaoException;
//    boolean updatePassword(String login, String newPassword) throws DaoException;
//    boolean isLoginAvailable(String login) throws DaoException;
//    boolean isEmailAvailable(String email) throws DaoException;


}
