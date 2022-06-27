package by.ashurmatov.anime.model.dao;

import by.ashurmatov.anime.exception.DaoException;
import by.ashurmatov.anime.model.entity.AbstractEntity;
import by.ashurmatov.anime.model.entity.User;
import by.ashurmatov.anime.model.entity.type.UserRole;

import java.util.List;
import java.util.Optional;

public interface BaseDao<T extends AbstractEntity>{
    boolean insert(T t) throws DaoException;
    boolean delete(T t) throws DaoException;
    List<T> findAll() throws DaoException;
    boolean update(T t) throws DaoException;
    Optional<T> findById(Long id) throws DaoException;

    Optional<T> findByLogin(String login) throws DaoException;
    UserRole findUserRole(String login) throws DaoException;
    List<T> findUsersByRole(UserRole userRole) throws DaoException;
    boolean deleteByLogin(String login) throws DaoException;
    boolean updateUser(User user) throws DaoException;
    boolean updatePassword(String login, String newPassword) throws DaoException;
    boolean isLoginAvailable(String login) throws DaoException;
    boolean isEmailAvailable(String email) throws DaoException;


}
