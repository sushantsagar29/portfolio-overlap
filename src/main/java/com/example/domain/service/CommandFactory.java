package com.example.domain.service;

import com.example.infra.model.Command;

public class CommandFactory {

    private final CurrentPortfolioCommand currentPortfolioCommand;
    private final AddStockCommand addStockCommand;
    private final PrintOverlapCommand printOverlapCommand;
    private final UnsupportedCommand unsupportedCommand;

    public CommandFactory(CurrentPortfolioCommand currentPortfolioCommand,
                          AddStockCommand addStockCommand,
                          PrintOverlapCommand printOverlapCommand,
                          UnsupportedCommand unsupportedCommand) {
        this.currentPortfolioCommand = currentPortfolioCommand;
        this.addStockCommand = addStockCommand;
        this.printOverlapCommand = printOverlapCommand;
        this.unsupportedCommand = unsupportedCommand;
    }

    public PortfolioCommand fetchCommandExecuter(Command command) {
        switch (command.getCommandType()) {
            case CURRENT_PORTFOLIO:
                return currentPortfolioCommand;
            case CALCULATE_OVERLAP:
                return printOverlapCommand;
            case ADD_STOCK:
                return addStockCommand;
            default:
                return unsupportedCommand;
        }
    }
}
