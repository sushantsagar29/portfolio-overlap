package com.example.domain.service;

import com.example.domain.Portfolio;
import com.example.infra.model.Command;

import java.util.List;

public class CurrentPortfolioCommand implements PortfolioCommand{

    private final Portfolio portfolio;

    public CurrentPortfolioCommand(Portfolio portfolio) {
        this.portfolio = portfolio;
    }

    @Override
    public void execute(Command command) {
        List<String> mutualFundsInPortfolio = command.getArguments();
        portfolio.setCurrentPortfolio(mutualFundsInPortfolio);
    }
}
