package by.ashurmatov.anime.controller.command.impl;

import by.ashurmatov.anime.controller.attribute.ParameterName;
import by.ashurmatov.anime.controller.command.Command;
import by.ashurmatov.anime.controller.command.Router;
import by.ashurmatov.anime.controller.path.PagePath;
import by.ashurmatov.anime.entity.Anime;
import by.ashurmatov.anime.entity.Comment;
import by.ashurmatov.anime.entity.Rating;
import by.ashurmatov.anime.exception.CommandException;
import by.ashurmatov.anime.exception.ServiceException;
import by.ashurmatov.anime.service.AnimeService;
import by.ashurmatov.anime.service.CommentService;
import by.ashurmatov.anime.service.RatingService;
import by.ashurmatov.anime.service.impl.AnimeServiceImpl;
import by.ashurmatov.anime.service.impl.CommentServiceImpl;
import by.ashurmatov.anime.service.impl.RatingServiceImpl;
import by.ashurmatov.anime.validator.AnimeValidator;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.util.Optional;

public class AnimeAddCommand implements Command {
    private static final Logger logger = LogManager.getLogger(AnimeAddCommand.class);
    @Override
    public Router execute(HttpServletRequest request, HttpServletResponse response) throws CommandException{
        Router router;
        AnimeService animeService = AnimeServiceImpl.getInstance();
        RatingService ratingService = RatingServiceImpl.getInstance();
        CommentService commentService = CommentServiceImpl.getInstance();
        String animeName = request.getParameter(ParameterName.ANIME_NAME);
        String country = request.getParameter(ParameterName.COUNTRY_NAME);
        String createdYearString = request.getParameter(ParameterName.CREATED_YEAR);
        String genre = request.getParameter(ParameterName.GENRE);
        String ageLimitString = request.getParameter(ParameterName.AGE_LIMIT);
        String description = request.getParameter(ParameterName.DESCRIPTION);
        String imagePath = request.getParameter(ParameterName.IMAGE_PATH);



        logger.info("Anime name is " + animeName);
        logger.info("Country of anime is  " + country);
        logger.info("Created Year is  " + createdYearString);
        logger.info("Genre of anime:   " + genre);
        logger.info("Age limit : " + ageLimitString);
        logger.info("Description of anime: " + description);
        logger.info("Image path of picture of anime " + imagePath);

        if (!AnimeValidator.validateInput(animeName,country,createdYearString,genre,ageLimitString,description,imagePath)) {
            logger.error("Invalid input");
            router = new Router(PagePath.ADD_PAGE,Router.Type.FORWARD);
            return router;
        }
        int createdYear = Integer.parseInt(createdYearString);
        int ageLimit = Integer.parseInt(ageLimitString);
        boolean isValidated = AnimeValidator.validateAnime(animeName,country,createdYear,genre,ageLimit,description,imagePath);

        try {
            if (isValidated) {
                if (animeService.isAnimeNameAvailable(animeName)) {
                    Anime anime = new Anime();
                    anime.setName(animeName);
                    anime.setCountry(country);
                    anime.setCreatedYear(createdYear);
                    anime.setGenre(genre);
                    anime.setAgeLimit(ageLimit);
                    anime.setDescription(description);
                    anime.setImage_path(imagePath);

                    if (animeService.register(anime)) {
                        Optional<Integer> optionalIdOfAnime = animeService.findIdByName(anime.getName());
                        if (optionalIdOfAnime.isEmpty()) {
                            logger.error("optionalIdOfAnime is empty");
                            throw new CommandException("optionalIdOfAnime is empty");
                        }
                        Rating rating = new Rating();
                        rating.setAnime_id(optionalIdOfAnime.get());
                        rating.setValue(0);

                        Comment comment = new Comment();
                        comment.setComment_text("Hello World");
                        comment.setAnime_id(optionalIdOfAnime.get());

                        if (ratingService.register(rating) && commentService.register(comment)) {
                            logger.info("Row of rating and  row of comment  are created with new Anime");
                            router = new AdminAllAnimeCommand().execute(request,response);
                            return router;
                        }else {
                            logger.error("row of rating and row of comment  are not created with new Anime");
                            throw new CommandException("row of rating and row of comment  are not created with new Anime");
                        }

                    } else {
                        logger.error("Anime is not saved to Database ");
                        return new Router(PagePath.ADD_PAGE,Router.Type.FORWARD);
                    }
                } else {
                    logger.error("Anime is already in Database ");
                    return new AdminAllAnimeCommand().execute(request,response);
                }

            } else {
                logger.error("Anime is not validated ");
                return new Router(PagePath.ADD_PAGE,Router.Type.FORWARD);
            }
        }catch (ServiceException serviceException) {
            logger.error("Error in registering anime " + serviceException);
            throw new CommandException(serviceException);
        }

    }
}
