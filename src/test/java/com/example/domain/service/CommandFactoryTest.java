package com.example.domain.service;

import com.example.infra.model.Command;
import com.example.infra.model.CommandType;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.Collections;

import static org.junit.jupiter.api.Assertions.*;

class CommandFactoryTest {

    private CommandFactory commandFactory;

    @Mock
    private CurrentPortfolioCommand currentPortfolioCommand;

    @Mock
    private AddStockCommand addStockCommand;

    @Mock
    private PrintOverlapCommand printOverlapCommand;

    @Mock
    private UnsupportedCommand unsupportedCommand;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.initMocks(this);
        commandFactory = new CommandFactory(currentPortfolioCommand, addStockCommand, printOverlapCommand, unsupportedCommand);
    }

    @Test
    void shouldFetchCommandExecuterWithCurrentPortfolioCommand() {
        Command command = new Command(CommandType.CURRENT_PORTFOLIO, Collections.emptyList());
        assertEquals(currentPortfolioCommand, commandFactory.fetchCommandExecuter(command));
    }

    @Test
    void shouldFetchCommandExecuterWithAddStockCommand() {
        Command command = new Command(CommandType.ADD_STOCK, Collections.emptyList());
        assertEquals(addStockCommand, commandFactory.fetchCommandExecuter(command));
    }

    @Test
    void shouldFetchCommandExecuterWithPrintOverlapCommand() {
        Command command = new Command(CommandType.CALCULATE_OVERLAP, Collections.emptyList());
        assertEquals(printOverlapCommand, commandFactory.fetchCommandExecuter(command));
    }
}