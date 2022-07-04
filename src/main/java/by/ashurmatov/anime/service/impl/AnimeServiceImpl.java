package by.ashurmatov.anime.service.impl;

import by.ashurmatov.anime.dao.impl.AnimeDaoImpl;
import by.ashurmatov.anime.entity.Anime;
import by.ashurmatov.anime.exception.DaoException;
import by.ashurmatov.anime.exception.ServiceException;
import by.ashurmatov.anime.service.AnimeService;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.util.List;
import java.util.Optional;

public class AnimeServiceImpl implements AnimeService {
    private static final Logger logger = LogManager.getLogger(AnimeServiceImpl.class);
    private static AnimeDaoImpl animeDao = AnimeDaoImpl.getInstance();
    private static AnimeServiceImpl instance = new AnimeServiceImpl();
    private AnimeServiceImpl() {

    }

    public static AnimeServiceImpl getInstance() {
        return instance;
    }

    @Override
    public boolean editAnime(Anime anime, Long id) throws ServiceException {
        try {
            return animeDao.editAnime(anime, id);
        }catch (DaoException daoException) {
            logger.error("Error in updating Anime " + daoException);
            throw new ServiceException(daoException);
        }
    }

    @Override
    public boolean isAnimeNameAvailable(String animeName) throws ServiceException {
        try {
            return animeDao.isAnimeNameAvailable(animeName);
        }catch (DaoException daoException) {
            logger.error("Error in checking name in Database " + daoException);
            throw new ServiceException(daoException);
        }
    }

    @Override
    public Optional<Integer> findIdByName(String name) throws ServiceException {
        try {
            return animeDao.findIdByName(name);
        }catch (DaoException daoException) {
            logger.error("Error in finding anime by his name " + daoException);
            throw new ServiceException(daoException);
        }
    }

    @Override
    public boolean register(Anime anime) throws ServiceException {
        boolean isSaved = false;
        try {
            isSaved = animeDao.insert(anime);
        }catch (DaoException daoException) {
            logger.error("Error in registration anime " + daoException);
            throw new ServiceException(daoException);
        }
        return isSaved;
    }

    @Override
    public boolean delete(Long id) throws ServiceException {
        try {
            return animeDao.delete(id);
        }catch (DaoException daoException) {
            logger.error("Error in deleting anime " + daoException);
            throw new ServiceException(daoException);
        }
    }

    @Override
    public List<Anime> findAll() throws ServiceException {
        try {
            return animeDao.findAll();
        }catch (DaoException daoException) {
            logger.error("Error in finding all anime in Database "+ daoException);
            throw new ServiceException(daoException);
        }
    }

    @Override
    public Optional<Anime> findById(Long id) throws ServiceException {
        try {
            return animeDao.findById(id);
        }catch (DaoException daoException) {
            logger.error("Error in finding anime by id " + daoException);
            throw new ServiceException(daoException);
        }
    }
}
