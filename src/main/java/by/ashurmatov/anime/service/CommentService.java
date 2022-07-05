package by.ashurmatov.anime.service;

import by.ashurmatov.anime.entity.Comment;
import by.ashurmatov.anime.exception.DaoException;
import by.ashurmatov.anime.exception.ServiceException;

import java.util.List;

public interface CommentService extends BaseService<Comment> {
    List<Comment> findAllCommentByAnimeId(long id) throws ServiceException;
    boolean deleteAllCommentByAnime(long id) throws ServiceException;
}
