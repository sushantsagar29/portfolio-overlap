package com.example.infra.model;

import java.util.List;

public class Command {
    private final CommandType commandType;
    private final List<String> arguments;

    public Command(CommandType commandType, List<String> arguments) {
        this.commandType = commandType;
        this.arguments = arguments;
    }

    public CommandType getCommandType() {
        return commandType;
    }

    public List<String> getArguments() {
        return arguments;
    }
}
