package by.ashurmatov.anime.service;

import by.ashurmatov.anime.exception.ServiceException;
import by.ashurmatov.anime.entity.User;
import by.ashurmatov.anime.entity.type.UserRole;

import java.util.List;
import java.util.Optional;

public interface BaseService<T> {

    boolean register(T t) throws ServiceException;
    boolean delete(Long id) throws ServiceException;
    List<T> findAll() throws ServiceException;
    Optional<T> findById(Long id) throws ServiceException;

//    Optional<T> findByLogin(String login) throws ServiceException;
//    UserRole findUserRole(String login) throws ServiceException;
//    List<T> findUsersByRole(UserRole userRole) throws ServiceException;
//    boolean deleteByLogin(String login) throws ServiceException;
//    boolean updateUser(User user) throws ServiceException;
//    boolean updatePassword(String login, String newPassword) throws ServiceException;
//    boolean isLoginAvailable(String login) throws ServiceException;
//    boolean isEmailAvailable(String email) throws ServiceException;


}
