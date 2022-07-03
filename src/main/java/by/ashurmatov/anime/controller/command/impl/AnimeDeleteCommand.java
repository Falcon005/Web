package by.ashurmatov.anime.controller.command.impl;

import by.ashurmatov.anime.controller.attribute.ParameterName;
import by.ashurmatov.anime.controller.command.Command;
import by.ashurmatov.anime.controller.command.Router;
import by.ashurmatov.anime.entity.Anime;
import by.ashurmatov.anime.exception.CommandException;
import by.ashurmatov.anime.exception.ServiceException;
import by.ashurmatov.anime.service.AnimeService;
import by.ashurmatov.anime.service.impl.AnimeServiceImpl;
import jakarta.servlet.http.HttpServletRequest;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.util.Optional;

public class AnimeDeleteCommand implements Command {

    private static final Logger logger = LogManager.getLogger(AnimeDeleteCommand.class);
    @Override
    public Router execute(HttpServletRequest request) throws CommandException{
        Router router = null;
        AnimeService animeService = AnimeServiceImpl.getInstance();
        long id = Long.parseLong(request.getParameter(ParameterName.ANIME_ID));
        logger.info("id of Anime is " + id);
        try {
            Optional<Anime> optionalAnime = animeService.findById(id);
            String animeToString = "";
            if (optionalAnime.isPresent()) {
                animeToString = optionalAnime.get().toString();
            }
            if (animeService.delete(id)) {
                logger.info("Anime which id is " + id + " successfully deleted");
                router = new AdminAllAnimeCommand().execute(request);
                logger.info("Router is " + router + " in AnimeDeleteCommand");
                request.setAttribute(ParameterName.ANIME_DELETED,animeToString);
            } else {
                router = new AdminAllAnimeCommand().execute(request);
                logger.error("Anime which id is " + id + " not deleted");
                request.setAttribute(ParameterName.ANIME_NOT_DELETED,ParameterName.ANIME_NOT_DELETED);
            }
        }catch (ServiceException serviceException) {
            logger.error("Error in deleting anime " + serviceException);
            throw new CommandException(serviceException);
        }
        logger.info("Router is " + router);
        return router;
    }
}
