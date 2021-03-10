package com.tritpo.training.command;

import com.tritpo.training.exception.UnknownEntityException;

import java.util.Arrays;

public enum CommandType {
    SIGN_UP("signUp"),
    SIGN_IN("signIn"),
    TO_SIGN_IN("toSignIn"),
    MAIN_PAGE("mainPage"),
    TO_SIGN_UP("toSignUp");

    private String commandName;

    CommandType(String commandName) {
        this.commandName = commandName;
    }

    public String getCommandName() {
        return commandName;
    }

    public static CommandType valueOfName(String commandName) {
        CommandType[] commandTypes = CommandType.values();
        return Arrays.stream(commandTypes)
                .filter(type -> type.getCommandName().equals(commandName))
                .findFirst()
                .orElseThrow(() -> new UnknownEntityException("Command name not found"));
    }
}
