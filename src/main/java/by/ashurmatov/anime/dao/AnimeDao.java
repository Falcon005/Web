package by.ashurmatov.anime.dao;

import by.ashurmatov.anime.entity.Anime;
import by.ashurmatov.anime.exception.DaoException;

public interface AnimeDao extends BaseDao<Anime>{
    boolean editAnime(Anime anime, Long id) throws DaoException;
    boolean isAnimeNameAvailable(String animeName) throws DaoException;
}
