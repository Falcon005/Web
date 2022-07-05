package by.ashurmatov.anime.service.impl;

import by.ashurmatov.anime.dao.impl.RatingDaoImpl;
import by.ashurmatov.anime.entity.Rating;
import by.ashurmatov.anime.exception.DaoException;
import by.ashurmatov.anime.exception.ServiceException;
import by.ashurmatov.anime.service.RatingService;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.util.List;
import java.util.Optional;

public class RatingServiceImpl implements RatingService {
    private static final Logger logger = LogManager.getLogger(RatingServiceImpl.class);
    private RatingDaoImpl ratingDao = RatingDaoImpl.getInstance();
    private static RatingServiceImpl instance = new RatingServiceImpl();

    public static RatingServiceImpl getInstance() {
        return instance;
    }

    @Override
    public boolean register(Rating rating) throws ServiceException {
        boolean isSaved;
        try {
            isSaved = ratingDao.insert(rating);
        }catch (DaoException daoException) {
            logger.error("Error in registration rating " + daoException);
            throw new ServiceException(daoException);
        }
        return isSaved;
    }

    @Override
    public boolean delete(Long id) throws ServiceException {
        try {
            return ratingDao.delete(id);
        }catch (DaoException daoException) {
            logger.error("Error in deleting rating " + daoException);
            throw new ServiceException(daoException);
        }
    }

    @Override
    public List<Rating> findAll() throws ServiceException {
        try {
            return ratingDao.findAll();
        }catch (DaoException daoException) {
            logger.error("Error in finding all rates in Database "+ daoException);
            throw new ServiceException(daoException);
        }
    }

    @Override
    public Optional<Rating> findById(Long id) throws ServiceException {
        try {
            return ratingDao.findById(id);
        }catch (DaoException daoException) {
            logger.error("Error in finding rating by id " + daoException);
            throw new ServiceException(daoException);
        }
    }

    @Override
    public Optional<Rating> findRatingByAnimeId(Long id) throws ServiceException {
        try {
            return ratingDao.findRatingByAnimeId(id);
        }catch (DaoException daoException) {
            logger.error("Error in finding rating by anime  id " + daoException);
            throw new ServiceException(daoException);
        }
    }

    @Override
    public boolean updateValue(long id, double value) throws ServiceException {
        try {
            return ratingDao.updateValue(id,value);
        }catch (DaoException daoException) {
            logger.error("Error in updating rating value by anime  id " + daoException);
            throw new ServiceException(daoException);
        }
    }

    @Override
    public boolean deleteRatingByAnimeId(long id) throws ServiceException {
        try {
            return ratingDao.deleteRatingByAnimeId(id);
        }catch (DaoException daoException) {
            logger.error("Error in deleting row of rating  by anime id " + daoException);
            throw new ServiceException(daoException);
        }
    }
}
