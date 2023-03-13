package com.example.domain.service;

import com.example.domain.Portfolio;
import com.example.infra.model.Command;
import com.example.infra.model.CommandType;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;

class CurrentPortfolioCommandTest {

    private CurrentPortfolioCommand currentPortfolioCommand;

    private Portfolio portfolio;

    @BeforeEach
    void setUp() {
        portfolio = new Portfolio();
        currentPortfolioCommand = new CurrentPortfolioCommand(portfolio);
    }

    @Test
    void shouldSetFundsToCurrentPortfolio() {
        List<String> mutualFundsInPortfolio = List.of("fund1", "fund2");
        Command command = new Command(CommandType.CURRENT_PORTFOLIO, mutualFundsInPortfolio);

        currentPortfolioCommand.execute(command);

        assertEquals(2, portfolio.getMutualFunds().size());
    }
}