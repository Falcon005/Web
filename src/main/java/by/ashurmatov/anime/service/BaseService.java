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

}
