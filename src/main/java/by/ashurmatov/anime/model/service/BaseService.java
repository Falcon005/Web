package by.ashurmatov.anime.model.service;

import java.util.List;
import java.util.Optional;
import java.util.logging.Logger;

public interface BaseService<T> {

    boolean add(T t);

    boolean delete(Long id);

    boolean update(T t);

    Optional<T> findById(Long id);

    List<T> findAll();
}
