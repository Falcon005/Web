package by.ashurmatov.anime.controller.command;

import by.ashurmatov.anime.controller.command.impl.*;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public enum CommandType {

    ADMIN_ALL_ANIME(new AdminAllAnimeCommand()),
    COMMENT_FOR_USER(new CommentForUserCommand()),
    ADMIN_USERS(new AdminUsersCommand()),
    ANIME_ADD(new AnimeAddCommand()),
    ANIME_DELETE(new AnimeDeleteCommand()),
    ANIME_EDIT(new AnimeEditCommand()),
    LOGIN(new LoginCommand()),
    LOGOUT(new LogoutCommand()),
    RATING(new RatingCommand()),
    RATING_FOR_USER(new RatingForUserCommand()),
    REGISTER(new RegisterCommand()),
    USER_BLOCK(new UserBlockCommand()),
    USER_ACTIVATE(new UserActivateCommand()),
    FIND_ANIME_TO_UPDATE(new FindAnimeToUpdate()),
    HOME_ALL_ANIME(new HomeAllAnimeCommand()),
    ONE_PAGE_FOR_ALL_ANIME(new OnePageForAllAnimeCommand()),
    ONE_PAGE_FOR_ALL_ANIME_FOR_USER(new OnePageForAllAnimeForUserCommand()),
    HOME_ALL_ANIME_FOR_USER(new HomeAllAnimeForUserCommand()),
    DEFAULT_COMMAND(new DefaultCommand());

    Command command;

    CommandType(Command command) {
        this.command = command;
    }
    public static Command defineCommand(String commandStr){
        final Logger logger = LogManager.getLogger();
        CommandType currentType = CommandType.DEFAULT_COMMAND;
        if (commandStr == null && commandStr.isBlank()) {
            return currentType.command;
        }

        try {
            currentType = CommandType.valueOf(commandStr.toUpperCase());
            return currentType.command;
        }catch (IllegalArgumentException illegalArgumentException) {
            logger.error("Unknown command exception " + illegalArgumentException);
            return currentType.command;
        }
    }

    public Command getCommand() {
        return command;
    }
}
