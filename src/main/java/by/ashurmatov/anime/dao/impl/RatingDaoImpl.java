package by.ashurmatov.anime.dao.impl;

import by.ashurmatov.anime.dao.RatingDao;
import by.ashurmatov.anime.dao.mapper.impl.RatingMapper;
import by.ashurmatov.anime.dao.query.RatingQuery;
import by.ashurmatov.anime.entity.Rating;
import by.ashurmatov.anime.exception.DaoException;
import by.ashurmatov.anime.pool.DynamicConnectionPool;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class RatingDaoImpl implements RatingDao {
    private static final Logger logger = LogManager.getLogger(RatingDaoImpl.class);
    private RatingMapper ratingMapper = new RatingMapper();
    private static RatingDaoImpl instance = new RatingDaoImpl();

    public static RatingDaoImpl getInstance() {
        return instance;
    }

    @Override
    public boolean insert(Rating rating) throws DaoException {
        try(Connection connection = DynamicConnectionPool.getInstance().provideConnection();
            PreparedStatement statement = connection.prepareStatement(RatingQuery.INSERT_RATING_QUERY)) {
            statement.setInt(1, rating.getAnime_id());
            statement.setDouble(2, rating.getValue());

            int count = statement.executeUpdate();
            return count == 1;
        }catch (SQLException sqlException) {
            logger.error("Error in inserting Rating into Database ");
            throw new DaoException(sqlException);
        }
    }

    @Override
    public boolean delete(Long id) throws DaoException {
        try(Connection connection = DynamicConnectionPool.getInstance().provideConnection();
            PreparedStatement statement = connection.prepareStatement(RatingQuery.DELETE_RATING_BY_ID)) {
            statement.setLong(1, id);
            int count = statement.executeUpdate();
            return count == 1;
        }catch (SQLException sqlException) {
            logger.error("Error in deleting the Rating By id");
            throw new DaoException(sqlException);
        }
    }

    @Override
    public List<Rating> findAll() throws DaoException {
        try (Connection connection = DynamicConnectionPool.getInstance().provideConnection();
            PreparedStatement statement = connection.prepareStatement(RatingQuery.SELECT_ALL_RATING)) {
            try(ResultSet resultSet = statement.executeQuery()) {
                List<Rating> ratingList = new ArrayList<>();
                while (resultSet.next()) {
                    Optional<Rating> optionalRating = ratingMapper.map(resultSet);
                    optionalRating.ifPresent(ratingList::add);
                }
                return ratingList;
            }
        }catch (SQLException sqlException) {
            logger.error("Error in connecting database " + sqlException);
            throw new DaoException(sqlException);
        }
    }

    @Override
    public Optional<Rating> findById(Long id) throws DaoException {
        try (Connection connection = DynamicConnectionPool.getInstance().provideConnection();
            PreparedStatement statement = connection.prepareStatement(RatingQuery.FIND_BY_ID)) {
            statement.setLong(1, id);
            try (ResultSet resultSet = statement.executeQuery()){
                Optional<Rating> rating;
                if (resultSet.next()) {
                    rating = ratingMapper.map(resultSet);
                    return rating;
                }
            }
        }catch (SQLException sqlException) {
            logger.error("Error in finding Rating  By id in Database");
            throw new DaoException(sqlException);
        }
        return Optional.empty();
    }

    @Override
    public Optional<Rating> findRatingByAnimeId(Long id) throws DaoException {
        try (Connection connection = DynamicConnectionPool.getInstance().provideConnection();
             PreparedStatement statement = connection.prepareStatement(RatingQuery.FIND_RATING_BY_ANIME_ID)) {
            statement.setLong(1, id);
            try (ResultSet resultSet = statement.executeQuery()){
                Optional<Rating> rating;
                if (resultSet.next()) {
                    rating = ratingMapper.map(resultSet);
                    return rating;
                }
            }
        }catch (SQLException sqlException) {
            logger.error("Error in finding Rating  By id in Database");
            throw new DaoException(sqlException);
        }
        return Optional.empty();
    }

    @Override
    public boolean updateValue(long id, double value) throws DaoException {
        try (Connection connection = DynamicConnectionPool.getInstance().provideConnection();
            PreparedStatement statement = connection.prepareStatement(RatingQuery.UPDATE_VALUE)) {
            statement.setDouble(1,value);
            statement.setLong(2,id);
            int count = statement.executeUpdate();
            return count == 1;
        }catch (SQLException sqlException) {
            logger.error("Error in updating value " + sqlException);
            throw new DaoException(sqlException);
        }
    }

    @Override
    public boolean deleteRatingByAnimeId(long id) throws DaoException{
        try(Connection connection = DynamicConnectionPool.getInstance().provideConnection();
            PreparedStatement statement = connection.prepareStatement(RatingQuery.DELETE_RATING_BY_ANIME_ID)) {
            statement.setLong(1, id);
            int count = statement.executeUpdate();
            return count == 1;
        }catch (SQLException sqlException) {
            logger.error("Error in deleting the Rating By Anime id");
            throw new DaoException(sqlException);
        }
    }
}
