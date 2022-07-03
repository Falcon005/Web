package by.ashurmatov.anime.controller.command.impl;

import by.ashurmatov.anime.controller.attribute.ParameterName;
import by.ashurmatov.anime.controller.command.Command;
import by.ashurmatov.anime.controller.command.Router;
import by.ashurmatov.anime.controller.path.PagePath;
import by.ashurmatov.anime.entity.Anime;
import by.ashurmatov.anime.exception.CommandException;
import by.ashurmatov.anime.exception.ServiceException;
import by.ashurmatov.anime.service.AnimeService;
import by.ashurmatov.anime.service.impl.AnimeServiceImpl;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpSession;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.util.Optional;

public class FindAnimeToUpdate implements Command {
    private static final Logger logger = LogManager.getLogger(FindAnimeToUpdate.class);
    @Override
    public Router execute(HttpServletRequest request) throws CommandException {
        HttpSession session = request.getSession();
        AnimeService animeService = AnimeServiceImpl.getInstance();
        Anime toUpdate;
        long id = Long.parseLong(request.getParameter(ParameterName.ANIME_ID));
        try {
            Optional<Anime> optionalAnime = animeService.findById(id);
            if (optionalAnime.isEmpty()) {
                throw new ServiceException("could not find the user with id number: " + id);
            }
            toUpdate = optionalAnime.get();
            session.setAttribute(ParameterName.TEMPORARY_ANIME,toUpdate);
            session.setAttribute(ParameterName.ANIME_ID,id);
            return new Router(PagePath.EDIT_PAGE,Router.Type.FORWARD);
        }catch (ServiceException serviceException) {
            logger.error("error in finding the anime by id  + " +  serviceException);
            throw new CommandException(serviceException);
        }
    }
}
