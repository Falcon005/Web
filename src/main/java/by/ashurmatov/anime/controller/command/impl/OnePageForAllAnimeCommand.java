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
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.util.Optional;

public class OnePageForAllAnimeCommand implements Command {
    private static final Logger logger = LogManager.getLogger(OnePageForAllAnimeCommand.class);
    @Override
    public Router execute(HttpServletRequest request) throws CommandException {
        AnimeService animeService = AnimeServiceImpl.getInstance();
        Router router;
        long id = Long.parseLong(request.getParameter(ParameterName.ANIME_ID));
        try {
            Optional<Anime> optionalAnime = animeService.findById(id);
            if (optionalAnime.isEmpty()) {
                throw new CommandException("could not find the anime with id number: " + id);
            }
            Anime anime = optionalAnime.get();
            request.setAttribute(ParameterName.TEMPORARY_ANIME,anime);
            router = new Router(PagePath.ONE_PAGE_FOR_ALL_ANIME,Router.Type.FORWARD);
            return router;
        }catch (ServiceException serviceException) {
            logger.error("Error in finding the anime by id " + serviceException);
            throw new CommandException(serviceException);
        }

    }
}
