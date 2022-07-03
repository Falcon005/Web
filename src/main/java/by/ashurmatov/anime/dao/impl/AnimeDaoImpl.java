package by.ashurmatov.anime.dao.impl;

import by.ashurmatov.anime.controller.attribute.CookieName;
import by.ashurmatov.anime.dao.AnimeDao;
import by.ashurmatov.anime.dao.mapper.impl.AnimeMapper;
import by.ashurmatov.anime.dao.query.AnimeQuery;
import by.ashurmatov.anime.entity.Anime;
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

public class AnimeDaoImpl implements AnimeDao {
    private static final Logger logger = LogManager.getLogger(AnimeDaoImpl.class);
    private final AnimeMapper animeMapper = new AnimeMapper();
    private static final AnimeDaoImpl instance = new AnimeDaoImpl();
    private AnimeDaoImpl(){

    }
    public static AnimeDaoImpl getInstance() {
        return instance;
    }
    @Override
    public boolean insert(Anime anime) throws DaoException {
        try (Connection connection = DynamicConnectionPool.getInstance().provideConnection();
            PreparedStatement statement = connection.prepareStatement(AnimeQuery.INSERT_ANIME_QUERY)) {
            statement.setString(1,anime.getName());
            statement.setString(2,anime.getCountry());
            statement.setInt(3,anime.getCreatedYear());
            statement.setString(4,anime.getGenre());
            statement.setInt(5,anime.getAgeLimit());
            statement.setString(6,anime.getDescription());
            statement.setString(7,anime.getImage_path());
            int count = statement.executeUpdate();
            return count == 1;
        }catch (SQLException sqlException) {
            logger.error("Error in inserting Anime into Database");
            throw new DaoException(sqlException);
        }
    }

    @Override
    public boolean delete(Long id) throws DaoException {
        try (Connection connection = DynamicConnectionPool.getInstance().provideConnection();
            PreparedStatement statement = connection.prepareStatement(AnimeQuery.DELETE_ANIME_BY_ID)) {
            statement.setLong(1,id);
            int count = statement.executeUpdate();
            return count == 1;
        }catch (SQLException sqlException) {
            logger.error("Error in deleting the Anime By id");
            throw new DaoException(sqlException);
        }
    }

    @Override
    public List<Anime> findAll() throws DaoException {
        try (Connection connection = DynamicConnectionPool.getInstance().provideConnection();
             PreparedStatement statement = connection.prepareStatement(AnimeQuery.SELECT_ALL_ANIME)) {
            try(ResultSet resultSet = statement.executeQuery()) {
                List<Anime> animeList = new ArrayList<>();
                while (resultSet.next()) {
                    Optional<Anime> optionalAnime = animeMapper.map(resultSet);
                    optionalAnime.ifPresent(animeList::add);
                }
                return animeList;
            }
        }catch (SQLException sqlException) {
                 logger.error("Error in connecting database " + sqlException);
                 throw new DaoException(sqlException);
        }
    }

    @Override
    public Optional<Anime> findById(Long id) throws DaoException {
        try (Connection connection = DynamicConnectionPool.getInstance().provideConnection();
            PreparedStatement statement = connection.prepareStatement(AnimeQuery.FIND_BY_ID)) {
            statement.setLong(1,id);
            try (ResultSet resultSet = statement.executeQuery()) {
                Optional<Anime> anime;
                if (resultSet.next()) {
                    anime = animeMapper.map(resultSet);
                    return anime;
                }
            }
        }catch (SQLException sqlException) {
            logger.error("Error in finding Anime By id in Database");
            throw new DaoException(sqlException);
        }
        return Optional.empty();
    }


    @Override
    public boolean editAnime(Anime anime, Long id) throws DaoException {
        try(Connection connection = DynamicConnectionPool.getInstance().provideConnection();
            PreparedStatement statement = connection.prepareStatement(AnimeQuery.EDIT_ANIME)) {
            statement.setString(1,anime.getName());
            statement.setString(2,anime.getCountry());
            statement.setInt(3,anime.getCreatedYear());
            statement.setString(4,anime.getGenre());
            statement.setInt(5,anime.getAgeLimit());
            statement.setString(6,anime.getDescription());
            statement.setString(7,anime.getImage_path());
            statement.setLong(8,id);
            int count = statement.executeUpdate();
            return  count == 1;
        }catch (SQLException sqlException) {
            logger.error("Error in editing Anime By id in Database " + sqlException);
            throw new DaoException(sqlException);
        }
    }

    @Override
    public boolean isAnimeNameAvailable(String animeName) throws DaoException {
        try(Connection connection = DynamicConnectionPool.getInstance().provideConnection();
            PreparedStatement statement = connection.prepareStatement(AnimeQuery.CHECK_NAME)) {
            statement.setString(1,animeName);
            logger.info(statement.toString());
            try(ResultSet resultSet = statement.executeQuery()) {
                boolean toReturn = !resultSet.next();
                return  toReturn;
            }
        }catch (SQLException sqlException) {
            logger.error("Error in connecting the Database " + sqlException);
            throw new DaoException(sqlException);
        }
    }


}
