package by.ashurmatov.anime.controller.command.impl;

import by.ashurmatov.anime.controller.attribute.ParameterName;
import by.ashurmatov.anime.controller.command.Command;
import by.ashurmatov.anime.controller.command.Router;
import by.ashurmatov.anime.controller.path.PagePath;
import by.ashurmatov.anime.entity.Anime;
import by.ashurmatov.anime.entity.Rating;
import by.ashurmatov.anime.exception.CommandException;
import by.ashurmatov.anime.exception.ServiceException;
import by.ashurmatov.anime.service.AnimeService;
import by.ashurmatov.anime.service.RatingService;
import by.ashurmatov.anime.service.impl.AnimeServiceImpl;
import by.ashurmatov.anime.service.impl.RatingServiceImpl;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.util.Optional;

public class OnePageForAllAnimeCommand implements Command {
    private static final Logger logger = LogManager.getLogger(OnePageForAllAnimeCommand.class);
    @Override
    public Router execute(HttpServletRequest request, HttpServletResponse response) throws CommandException {
        HttpSession session = request.getSession();
        AnimeService animeService = AnimeServiceImpl.getInstance();
        RatingService ratingService = RatingServiceImpl.getInstance();
        Router router;

        long id = Long.parseLong(request.getParameter(ParameterName.ANIME_ID));
        try {
            Optional<Anime> optionalAnime = animeService.findById(id);
            Optional<Rating> optionalRating = ratingService.findRatingByAnimeId(id);
            if (optionalAnime.isEmpty() || optionalRating.isEmpty()) {
                throw new CommandException("could not find the rating of anime  with id number: " + id);
            }
            Anime anime = optionalAnime.get();
            Rating rating = optionalRating.get();
            request.setAttribute(ParameterName.TEMPORARY_ANIME,anime);
            request.setAttribute(ParameterName.TEMPORARY_RATING,rating);
            session.setAttribute(ParameterName.CURRENT_PAGE, PagePath.ONE_PAGE_FOR_ALL_ANIME);
            router = new Router(PagePath.ONE_PAGE_FOR_ALL_ANIME,Router.Type.FORWARD);
            return router;
        }catch (ServiceException serviceException) {
            logger.error("Error in finding the anime by id " + serviceException);
            throw new CommandException(serviceException);
        }

    }
}
