package by.ashurmatov.anime.model.dao;

import by.ashurmatov.anime.exception.DaoException;
import by.ashurmatov.anime.model.entity.AbstractEntity;

import java.util.List;
import java.util.Optional;

public interface BaseDao<T extends AbstractEntity>{
    boolean insert(T t) throws DaoException;
    boolean delete(T t) throws DaoException;
    List<T> findAll() throws DaoException;
    T update(T t) throws DaoException;
    Optional<T> findById(Long id) throws DaoException;


}
