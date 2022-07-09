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
import by.ashurmatov.anime.validator.AnimeValidator;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.util.Optional;

public class AnimeEditCommand implements Command {
    private static final Logger logger = LogManager.getLogger(AnimeEditCommand.class);
    @Override
    public Router execute(HttpServletRequest request, HttpServletResponse response) throws CommandException{
        AnimeService animeService = AnimeServiceImpl.getInstance();
        HttpSession session = request.getSession();
        Anime animeForUpdate;
        long id = (long) session.getAttribute(ParameterName.ANIME_ID);
        logger.info("Anime id is " + id);


        try {
            Optional<Anime> optionalAnime = animeService.findById(id);
            if (optionalAnime.isEmpty()) {
                throw new CommandException("could not find the anime with id number: " + id);
            }
            animeForUpdate = optionalAnime.get();
            logger.info("retrieved anime from database is " + animeForUpdate);
            Router router;
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
            logger.info("Image path of anime: " + imagePath);

            if (!AnimeValidator.validateInput(animeName,country,createdYearString,genre,ageLimitString,description,imagePath)) {
                logger.error("Invalid input");
                router = new Router(PagePath.EDIT_PAGE,Router.Type.FORWARD);
                return router;
            }
            int createdYear = Integer.parseInt(createdYearString);
            int ageLimit = Integer.parseInt(ageLimitString);
            boolean isValidated = AnimeValidator.validateAnime(animeName,country,createdYear,genre,ageLimit,description,imagePath);

            if (isValidated) {
                animeForUpdate.setName(animeName);
                animeForUpdate.setCountry(country);
                animeForUpdate.setCreatedYear(createdYear);
                animeForUpdate.setGenre(genre);
                animeForUpdate.setAgeLimit(ageLimit);
                animeForUpdate.setDescription(description);
                animeForUpdate.setImage_path(imagePath);

                if (animeService.editAnime(animeForUpdate, id)) {
                    router = new AdminAllAnimeCommand().execute(request,response);
                    return router;
                } else {
                    logger.error("Anime is not edited to Database ");
                    return new Router(PagePath.EDIT_PAGE,Router.Type.FORWARD);
                }
            } else {
                logger.error("Anime is not validated ");
                return new Router(PagePath.EDIT_PAGE,Router.Type.FORWARD);
            }

        }catch (ServiceException serviceException) {
            logger.error("Error in editing anime " + serviceException);
            throw new CommandException(serviceException);
        }

    }
}
