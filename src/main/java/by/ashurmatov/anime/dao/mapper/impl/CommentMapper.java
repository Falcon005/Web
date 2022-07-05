package by.ashurmatov.anime.dao.mapper.impl;

import by.ashurmatov.anime.dao.mapper.ColumnName;
import by.ashurmatov.anime.dao.mapper.EntityMapper;
import by.ashurmatov.anime.entity.Comment;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Optional;

public class CommentMapper implements EntityMapper<Comment> {
    private static final Logger logger = LogManager.getLogger(CommentMapper.class);

    @Override
    public Optional<Comment> map(ResultSet resultSet) {
        try {
            Comment comment = new Comment();
            comment.setId(resultSet.getInt(ColumnName.COMMENT_ID));
            comment.setComment_text(resultSet.getString(ColumnName.COMMENT_TEXT));
            comment.setAnime_id(resultSet.getInt(ColumnName.ANIME_ID_IN_RATING));

            return Optional.of(comment);
        }catch (SQLException sqlException) {
            logger.error("Error in mapping  resultSet into an object (Comment)");
        }
        return Optional.empty();
    }
}
