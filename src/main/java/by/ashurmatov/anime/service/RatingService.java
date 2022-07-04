package by.ashurmatov.anime.service;

import by.ashurmatov.anime.entity.Rating;
import by.ashurmatov.anime.exception.DaoException;
import by.ashurmatov.anime.exception.ServiceException;

import java.util.Optional;

public interface RatingService extends BaseService<Rating> {
    Optional<Rating> findRatingByAnimeId(Long id) throws ServiceException;
    boolean updateValue(long id, double value) throws ServiceException;
    boolean deleteRatingByAnimeId(long id) throws ServiceException;
}
