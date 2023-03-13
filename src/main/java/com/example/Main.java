package com.example;

import com.example.domain.MutualFundMap;
import com.example.constants.Constants;
import com.example.domain.Portfolio;
import com.example.domain.service.*;
import com.example.infra.InputParser;
import com.example.infra.model.Command;
import com.example.service.OverlapService;

import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;

public class Main {
    public static void main(String[] args) {
        OverlapService overlapService = new OverlapService();
        MutualFundMap mutualFundMap = overlapService.initializeMutualFundMap(Constants.FUND_STORE_URL);

        Path inputFilePath = Paths.get(args[0]);
        InputParser inputParser = new InputParser();
        List<Command> commandList = inputParser.parseFromFile(inputFilePath);

        Portfolio portfolio = new Portfolio();
        ConsolePrinter consolePrinter = new ConsolePrinter();

        CurrentPortfolioCommand currentPortfolioCommand = new CurrentPortfolioCommand(portfolio);
        AddStockCommand addStockCommand = new AddStockCommand(mutualFundMap);
        PrintOverlapCommand printOverlapCommand = new PrintOverlapCommand(portfolio, mutualFundMap, consolePrinter);

        CommandFactory commandFactory = new CommandFactory(currentPortfolioCommand, addStockCommand, printOverlapCommand);

        commandList.forEach(command -> commandFactory
                .fetchCommandExecuter(command)
                .execute(command));
    }
}