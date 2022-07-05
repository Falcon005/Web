package by.ashurmatov.anime.dao.impl;

import by.ashurmatov.anime.controller.attribute.CookieName;
import by.ashurmatov.anime.dao.CommentDao;
import by.ashurmatov.anime.dao.mapper.impl.CommentMapper;
import by.ashurmatov.anime.dao.query.CommentQuery;
import by.ashurmatov.anime.entity.Comment;
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

public class CommentDaoImpl implements CommentDao {
    private static final Logger logger = LogManager.getLogger(CommentDaoImpl.class);
    private final CommentMapper commentMapper = new CommentMapper();
    private static final CommentDaoImpl instance = new CommentDaoImpl();
    private CommentDaoImpl() {

    }
    public static CommentDaoImpl getInstance() {
        return instance;
    }
    @Override
    public boolean insert(Comment comment) throws DaoException {
        try (Connection connection = DynamicConnectionPool.getInstance().provideConnection();
             PreparedStatement statement = connection.prepareStatement(CommentQuery.INSERT_COMMENT_QUERY)) {
            statement.setString(1, comment.getComment_text());
            statement.setInt(2, comment.getAnime_id());

            int count = statement.executeUpdate();
            return count == 1;
        }catch (SQLException sqlException) {
            logger.error("Error in inserting Comment into Database");
            throw new DaoException(sqlException);
        }
    }

    @Override
    public boolean delete(Long id) throws DaoException {
        try (Connection connection = DynamicConnectionPool.getInstance().provideConnection();
            PreparedStatement statement = connection.prepareStatement(CommentQuery.DELETE_COMMENT_BY_ID)) {
            statement.setLong(1, id);
            int count = statement.executeUpdate();
            return  count == 1;
        }catch (SQLException sqlException) {
            logger.error("Error in deleting the Comment by id " + sqlException);
            throw new DaoException(sqlException);
        }
    }

    @Override
    public List<Comment> findAll() throws DaoException {
        try (Connection connection = DynamicConnectionPool.getInstance().provideConnection();
            PreparedStatement statement = connection.prepareStatement(CommentQuery.SELECT_ALL_COMMENT);
             ResultSet resultSet = statement.executeQuery()) {
            List<Comment> commentList = new ArrayList<>();
            while (resultSet.next()) {
                Optional<Comment> optionalComment= commentMapper.map(resultSet);
                optionalComment.ifPresent(commentList::add);
            }
            return commentList;
        }catch (SQLException sqlException) {
            logger.error("Error in connecting database " + sqlException);
            throw new DaoException(sqlException);
        }
    }

    @Override
    public Optional<Comment> findById(Long id) throws DaoException {
        try (Connection connection = DynamicConnectionPool.getInstance().provideConnection();
            PreparedStatement statement = connection.prepareStatement(CommentQuery.FIND_BY_ID)) {
            statement.setLong(1,id);
            try (ResultSet resultSet = statement.executeQuery()) {
                Optional<Comment> comment;
                if (resultSet.next()) {
                    comment = commentMapper.map(resultSet);
                    return comment;
                }
            }
        }catch (SQLException sqlException) {
            logger.error("Error in finding comment by id " + sqlException);
            throw new DaoException(sqlException);
        }
        return Optional.empty();
    }

    @Override
    public List<Comment> findAllCommentByAnimeId(long id) throws DaoException{
        try (Connection connection = DynamicConnectionPool.getInstance().provideConnection();
            PreparedStatement statement = connection.prepareStatement(CommentQuery.FIND_COMMENT_BY_ANIME_ID)) {
            statement.setLong(1,id);
            try (ResultSet resultSet = statement.executeQuery()) {
                List<Comment> commentList = new ArrayList<>();
                while (resultSet.next()) {
                    Optional<Comment> optionalComment = commentMapper.map(resultSet);
                    optionalComment.ifPresent(commentList::add);
                }
                return commentList;
            }

        }catch (SQLException sqlException) {
            logger.error("Error in finding comments by anime id in Database " + sqlException);
            throw new DaoException(sqlException);
        }
    }

    @Override
    public boolean deleteAllCommentByAnime(long id) throws DaoException {
        try (Connection connection = DynamicConnectionPool.getInstance().provideConnection();
            PreparedStatement statement = connection.prepareStatement(CommentQuery.DELETE_COMMENT_BY_ANIME_ID)) {
            statement.setLong(1, id);
            int count = statement.executeUpdate();
            return count == 1;
        }catch (SQLException sqlException) {
            logger.error("Error in deleting the Comment by anime id " + sqlException);
            throw new DaoException(sqlException);
        }
    }
}
