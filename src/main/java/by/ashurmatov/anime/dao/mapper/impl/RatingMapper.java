package by.ashurmatov.anime.dao.mapper.impl;

import by.ashurmatov.anime.dao.mapper.ColumnName;
import by.ashurmatov.anime.dao.mapper.EntityMapper;
import by.ashurmatov.anime.entity.Rating;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Optional;

public class RatingMapper implements EntityMapper<Rating> {
    private static final Logger logger = LogManager.getLogger(RatingMapper.class);

    @Override
    public Optional<Rating> map(ResultSet resultSet) {
        try {
            Rating rating = new Rating();
            rating.setId(resultSet.getInt(ColumnName.RATING_ID));
            rating.setAnime_id(resultSet.getInt(ColumnName.ANIME_ID_IN_RATING));
            rating.setValue(resultSet.getDouble(ColumnName.VALUE));

            return Optional.of(rating);
        }catch (SQLException sqlException) {
            logger.error("Error in mapping resultSet into an object (Rating)");
        }
        return Optional.empty();

    }
}
