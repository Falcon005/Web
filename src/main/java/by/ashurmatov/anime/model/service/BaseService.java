package by.ashurmatov.anime.model.service;

import by.ashurmatov.anime.exception.ServiceException;

import java.util.List;
import java.util.Optional;
import java.util.logging.Logger;

public interface BaseService<T> {

    boolean add(T t) throws ServiceException;

    boolean delete(Long id) throws ServiceException;

    boolean update(T t) throws ServiceException;

    Optional<T> findById(Long id) throws ServiceException;

    List<T> findAll() throws ServiceException;
}
