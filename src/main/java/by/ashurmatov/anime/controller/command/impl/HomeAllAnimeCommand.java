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
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.util.List;

public class HomeAllAnimeCommand implements Command {
    private static final Logger logger = LogManager.getLogger(HomeAllAnimeCommand.class);
    @Override
    public Router execute(HttpServletRequest request, HttpServletResponse response) throws CommandException {
        AnimeService animeService = AnimeServiceImpl.getInstance();
        HttpSession session = request.getSession();
        logger.info("Current page is " + session.getAttribute(ParameterName.CURRENT_PAGE));
        Router router;
        try {
            List<Anime> animeList = animeService.findAll();
            request.setAttribute(ParameterName.ANIME_LIST,animeList);
            session.setAttribute(ParameterName.CURRENT_PAGE, PagePath.HOME_MOVIES_PAGE);
            router = new Router(PagePath.HOME_MOVIES_PAGE,Router.Type.FORWARD);
            return router;
        }catch (ServiceException serviceException) {
            logger.error("Error in getting all the Anime from Database " + serviceException);
            throw new CommandException(serviceException);
        }
    }
}
