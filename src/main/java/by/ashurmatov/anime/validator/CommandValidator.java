package by.ashurmatov.anime.validator;

import by.ashurmatov.anime.controller.command.Command;
import by.ashurmatov.anime.controller.command.CommandType;

public class CommandValidator {
    private CommandValidator() {

    }

    public static Command commandValidator(String commandStr) {
        CommandType currentType;
        for (CommandType commandType : CommandType.values()) {
            if (commandType == (CommandType.valueOf(commandStr.toUpperCase()))) {
                currentType = CommandType.valueOf(commandStr.toUpperCase());
                return currentType.getCommand();
            }
        }
        currentType = CommandType.DEFAULT_COMMAND;
        return currentType.getCommand();
    }
}
