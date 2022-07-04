package by.ashurmatov.anime.dao;

import by.ashurmatov.anime.entity.Anime;
import by.ashurmatov.anime.exception.DaoException;

import java.util.Optional;

public interface AnimeDao extends BaseDao<Anime>{
    boolean editAnime(Anime anime, Long id) throws DaoException;
    boolean isAnimeNameAvailable(String animeName) throws DaoException;
    Optional<Integer> findIdByName(String name) throws DaoException;
}
