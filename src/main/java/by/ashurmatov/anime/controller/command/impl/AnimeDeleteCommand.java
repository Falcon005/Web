package by.ashurmatov.anime.controller.command.impl;

import by.ashurmatov.anime.controller.attribute.ParameterName;
import by.ashurmatov.anime.controller.command.Command;
import by.ashurmatov.anime.controller.command.Router;
import by.ashurmatov.anime.entity.Anime;
import by.ashurmatov.anime.exception.CommandException;
import by.ashurmatov.anime.exception.ServiceException;
import by.ashurmatov.anime.service.AnimeService;
import by.ashurmatov.anime.service.CommentService;
import by.ashurmatov.anime.service.RatingService;
import by.ashurmatov.anime.service.impl.AnimeServiceImpl;
import by.ashurmatov.anime.service.impl.CommentServiceImpl;
import by.ashurmatov.anime.service.impl.RatingServiceImpl;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.util.Optional;

public class AnimeDeleteCommand implements Command {

    private static final Logger logger = LogManager.getLogger(AnimeDeleteCommand.class);
    @Override
    public Router execute(HttpServletRequest request, HttpServletResponse response) throws CommandException{
        Router router;
        AnimeService animeService = AnimeServiceImpl.getInstance();
        RatingService ratingService = RatingServiceImpl.getInstance();
        CommentService commentService = CommentServiceImpl.getInstance();
        long id = Long.parseLong(request.getParameter(ParameterName.ANIME_ID));
        logger.info("id of Anime is " + id);

        try {
            Optional<Anime> optionalAnime = animeService.findById(id);
            String animeToString = "";
            if (optionalAnime.isPresent()) {
                animeToString = optionalAnime.get().toString();
            }
            if (ratingService.deleteRatingByAnimeId(id) && commentService.deleteAllCommentByAnime(id) && animeService.delete(id)) {
                logger.info("Anime which id is " + id + " successfully deleted");
                logger.info("Row which has anime id is" + id + "successfully deleted");
                logger.info("Row(s) which has anime id is(are) " + id + "successfully deleted");
                router = new AdminAllAnimeCommand().execute(request,response);
                logger.info("Router is " + router + " in AnimeDeleteCommand");
                request.setAttribute(ParameterName.ANIME_DELETED,animeToString);
                request.setAttribute(ParameterName.ROW_DELETED, ParameterName.ROW_DELETED);
                request.setAttribute(ParameterName.ROW_OF_COMMENT_DELETED,ParameterName.ROW_OF_COMMENT_DELETED);
            } else {
                router = new AdminAllAnimeCommand().execute(request,response);
                logger.error("Anime which id is " + id + " not deleted");
                logger.error("Row which has anime id is " + id + " not deleted");
                logger.info("Row(s) which has anime id is(are) " + id + " not deleted");
                request.setAttribute(ParameterName.ANIME_NOT_DELETED,ParameterName.ANIME_NOT_DELETED);
                request.setAttribute(ParameterName.ROW_NOT_DELETED, ParameterName.ROW_NOT_DELETED);
                request.setAttribute(ParameterName.ROW_OF_COMMENT_NOT_DELETED, ParameterName.ROW_OF_COMMENT_NOT_DELETED);
            }
        }catch (ServiceException serviceException) {
            logger.error("Error in deleting anime " + serviceException);
            throw new CommandException(serviceException);
        }
        logger.info("Router is " + router);
        return router;
    }
}
