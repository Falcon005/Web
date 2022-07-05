package by.ashurmatov.anime.dao;

import by.ashurmatov.anime.entity.Comment;
import by.ashurmatov.anime.exception.DaoException;

import java.util.List;

public interface CommentDao extends BaseDao<Comment>{
    List<Comment> findAllCommentByAnimeId(long id) throws DaoException;
    boolean deleteAllCommentByAnime(long id) throws DaoException;
}
