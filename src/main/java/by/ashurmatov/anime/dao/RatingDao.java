package by.ashurmatov.anime.dao;

import by.ashurmatov.anime.entity.Rating;
import by.ashurmatov.anime.exception.DaoException;

import java.util.Optional;

public interface RatingDao extends BaseDao<Rating>{
    Optional<Rating> findRatingByAnimeId(Long id) throws DaoException;
    boolean updateValue(long id, double value) throws DaoException;
    boolean deleteRatingByAnimeId(long id) throws DaoException;
}
