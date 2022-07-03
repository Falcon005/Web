package by.ashurmatov.anime.dao.mapper.impl;

import by.ashurmatov.anime.dao.mapper.ColumnName;
import by.ashurmatov.anime.dao.mapper.EntityMapper;
import by.ashurmatov.anime.entity.Anime;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Optional;

public class AnimeMapper implements EntityMapper<Anime> {
    private static final Logger logger = LogManager.getLogger(AnimeMapper.class);
    @Override
    public Optional<Anime> map(ResultSet resultSet) {
        try {
            Anime anime = new Anime();
            anime.setId(resultSet.getInt(ColumnName.ANIME_ID));
            anime.setName(resultSet.getString(ColumnName.ANIME_NAME));
            anime.setCountry(resultSet.getString(ColumnName.COUNTRY));
            anime.setCreatedYear(resultSet.getInt(ColumnName.CREATED_YEAR));
            anime.setGenre(resultSet.getString(ColumnName.GENRE));
            anime.setAgeLimit(resultSet.getInt(ColumnName.AGE_LIMIT));
            anime.setDescription(resultSet.getString(ColumnName.DESCRIPTION));

            return Optional.of(anime);
        }catch (SQLException sqlException) {
            logger.error("Error in mapping resultSet into an object (Anime)");
        }
        return Optional.empty();
    }
}
