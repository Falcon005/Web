package by.ashurmatov.anime.service.impl;

import by.ashurmatov.anime.dao.impl.CommentDaoImpl;
import by.ashurmatov.anime.entity.Comment;
import by.ashurmatov.anime.exception.DaoException;
import by.ashurmatov.anime.exception.ServiceException;
import by.ashurmatov.anime.service.CommentService;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.util.List;
import java.util.Optional;

public class CommentServiceImpl implements CommentService {
    private static final Logger logger = LogManager.getLogger(CommentServiceImpl.class);
    private CommentDaoImpl commentDao = CommentDaoImpl.getInstance();
    private static CommentServiceImpl instance = new CommentServiceImpl();

    public static CommentServiceImpl getInstance() {
        return instance;
    }

    @Override
    public boolean register(Comment comment) throws ServiceException {
        boolean isSaved;
        try {
            isSaved = commentDao.insert(comment);
        }catch (DaoException daoException) {
            logger.error("Error in registration comment " + daoException);
            throw new ServiceException(daoException);
        }
        return isSaved;
    }

    @Override
    public boolean delete(Long id) throws ServiceException {
        try {
            return commentDao.delete(id);
        }catch (DaoException daoException) {
            logger.error("Error in deleting comment by his id " + daoException);
            throw new ServiceException(daoException);
        }
    }

    @Override
    public List<Comment> findAll() throws ServiceException {
        try {
            return commentDao.findAll();
        }catch (DaoException daoException) {
            logger.error("Error in finding all comments in Database "+ daoException);
            throw new ServiceException(daoException);
        }
    }

    @Override
    public Optional<Comment> findById(Long id) throws ServiceException {
        try {
            return commentDao.findById(id);
        }catch (DaoException daoException) {
            logger.error("Error in finding comment by id " + daoException);
            throw new ServiceException(daoException);
        }
    }

    @Override
    public List<Comment> findAllCommentByAnimeId(long id) throws ServiceException {
        try {
            return commentDao.findAllCommentByAnimeId(id);
        }catch (DaoException daoException) {
            logger.error("Error in finding comment by anime id " + daoException);
            throw new ServiceException(daoException);
        }
    }

    @Override
    public boolean deleteAllCommentByAnime(long id) throws ServiceException {
        try {
            return commentDao.deleteAllCommentByAnime(id);
        }catch (DaoException daoException) {
            logger.error("Error in deleting comment by anime id " + daoException);
            throw new ServiceException(daoException);
        }
    }
}
