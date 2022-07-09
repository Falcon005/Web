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

public class RatingCommand implements Command {
    private static final Logger logger = LogManager.getLogger(RatingCommand.class);
    @Override
    public Router execute(HttpServletRequest request, HttpServletResponse response) throws CommandException{
        HttpSession session = request.getSession();
        AnimeService animeService = AnimeServiceImpl.getInstance();
        RatingService ratingService = RatingServiceImpl.getInstance();
        Router router = new Router((String) session.getAttribute(ParameterName.CURRENT_PAGE),Router.Type.FORWARD);
        long id = Long.parseLong(request.getParameter(ParameterName.ANIME_ID));
        double value = Double.parseDouble(request.getParameter(ParameterName.RATE));


        try {
            Optional<Anime> optionalAnime = animeService.findById(id);
            if (ratingService.updateValue(id,value)) {
                Optional<Rating> optionalRating = ratingService.findRatingByAnimeId(id);
                if (optionalAnime.isEmpty() || optionalRating.isEmpty()) {
                    logger.error("optionalAnime or optionalRating is empty");
                    throw new CommandException("optionalAnime or optionalRating is empty");
                }
                Anime anime = optionalAnime.get();
                Rating rating = optionalRating.get();
                request.setAttribute(ParameterName.TEMPORARY_ANIME,anime);
                request.setAttribute(ParameterName.TEMPORARY_RATING,rating);
                session.setAttribute(ParameterName.CURRENT_PAGE, PagePath.ONE_PAGE_FOR_ALL_ANIME);
                return router;
            }else {
                logger.error("Couldn't update value of anime rating");
                throw new CommandException("Couldn't update value of anime rating");
            }
        }catch (ServiceException serviceException) {
            logger.error("Error in Rating command " + serviceException);
            throw new CommandException(serviceException);
        }

    }
}
