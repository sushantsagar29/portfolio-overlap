package com.example.infra;

import com.example.constants.Constants;
import com.example.exception.MalformedInputFileException;
import com.example.exception.UnsupportedCommandException;
import com.example.infra.model.Command;
import com.example.infra.model.CommandType;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

public class InputParser {
    public List<Command> parseFromFile(Path inputFilePath) {
        List<Command> commands;
        try {
            List<String> lines = Files.readAllLines(inputFilePath);
            commands = Collections.unmodifiableList(lines.stream().map(this::createCommand).collect(Collectors.toList()));
        } catch (IOException e) {
            throw new MalformedInputFileException(e.getMessage());
        }
        return commands;
    }

    private Command createCommand(String line) {
        return new Command(convertLineToCommandType(line), convertLineToArguments(line));
    }

    private CommandType convertLineToCommandType(String line) {
        try {
            return CommandType.valueOf(line.split(Constants.SPACE)[0]);
        } catch (IllegalArgumentException e) {
            throw new UnsupportedCommandException(line);
        }
    }

    private List<String> convertLineToArguments(String line) {
        List<String> args = Arrays.asList(line.split(Constants.SPACE));
        return args.subList(1, args.size());
    }
}
