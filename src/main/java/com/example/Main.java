package com.example;

import com.example.domain.MutualFundMap;
import com.example.constants.Constants;
import com.example.domain.Portfolio;
import com.example.domain.service.*;
import com.example.exception.UnsupportedCommandException;
import com.example.infra.InputParser;
import com.example.infra.model.Command;
import com.example.service.OverlapService;
import com.fasterxml.jackson.databind.ObjectMapper;

import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Collections;
import java.util.List;

public class Main {
    public static void main(String[] args) {
        ObjectMapper objectMapper = new ObjectMapper();
        OverlapService overlapService = new OverlapService();
        ConsolePrinter consolePrinter = new ConsolePrinter();
        MutualFundMap mutualFundMap = overlapService.initializeMutualFundMap(Constants.FUND_STORE_URL, objectMapper);

        Path inputFilePath = Paths.get(args[0]);
        InputParser inputParser = new InputParser();
        List<Command> commandList = Collections.emptyList();

        try {
            commandList = inputParser.parseFromFile(inputFilePath);
        }catch (UnsupportedCommandException ex){
            consolePrinter.print(ex.getMessage());
        }

        Portfolio portfolio = new Portfolio();


        CurrentPortfolioCommand currentPortfolioCommand = new CurrentPortfolioCommand(portfolio);
        AddStockCommand addStockCommand = new AddStockCommand(mutualFundMap, consolePrinter);
        PrintOverlapCommand printOverlapCommand = new PrintOverlapCommand(portfolio, mutualFundMap, consolePrinter);
        UnsupportedCommand unsupportedCommand = new UnsupportedCommand();

        CommandFactory commandFactory = new CommandFactory(currentPortfolioCommand,
                addStockCommand,
                printOverlapCommand,
                unsupportedCommand);

        commandList.forEach(command -> commandFactory
                .fetchCommandExecuter(command)
                .execute(command));
    }
}