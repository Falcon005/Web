package by.ashurmatov.anime.controller.command;

import by.ashurmatov.anime.controller.command.impl.*;

public enum CommandType {
    ADMIN_ALL_ANIME(new AdminAllAnimeCommand()),
    ADMIN_COMMAND(new AdminCommand()),
    ADMIN_USERS(new AdminUsersCommand()),
    ANIME_ADD(new AnimeAddCommand()),
    ANIME_DELETE(new AnimeDeleteCommand()),
    ANIME_EDIT(new AnimeEditCommand()),
    HOME(new HomeCommand()),
    LOGIN(new LoginCommand()),
    LOGOUT(new LogoutCommand()),
    PROFILE(new ProfileCommand()),
    PROFILE_EDIT(new ProfileEditCommand()),
    RATING(new RatingCommand()),
    REGISTER(new RegisterCommand()),
    USER_BLOCK(new UserBlockCommand()),
    USER_UNBLOCK(new UserUnblockCommand()),
    DEFAULT_COMMAND(new DefaultCommand());

    Command command;

    CommandType(Command command) {
        this.command = command;
    }
    public static Command defineCommand(String commandStr){
        CommandType currentType=CommandType.valueOf(commandStr.toUpperCase());
        return currentType.command;
    }
}
