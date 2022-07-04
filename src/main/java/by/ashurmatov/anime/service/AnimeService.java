package by.ashurmatov.anime.service;

import by.ashurmatov.anime.entity.Anime;
import by.ashurmatov.anime.exception.DaoException;
import by.ashurmatov.anime.exception.ServiceException;

import java.util.Optional;

public interface AnimeService extends BaseService<Anime>{
    boolean editAnime(Anime anime, Long id) throws ServiceException;
    boolean isAnimeNameAvailable(String animeName) throws ServiceException;
    Optional<Integer> findIdByName(String name) throws ServiceException;
}
