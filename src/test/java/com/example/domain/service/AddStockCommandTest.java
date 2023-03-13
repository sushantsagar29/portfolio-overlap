package com.example.domain.service;

import com.example.domain.MutualFundMap;
import com.example.exception.FundNotFoundException;
import com.example.infra.model.Command;
import com.example.infra.model.CommandType;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.List;

import static org.mockito.Mockito.doThrow;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.verifyNoMoreInteractions;
import static org.mockito.Mockito.verifyZeroInteractions;

class AddStockCommandTest {

    private AddStockCommand addStockCommand;

    @Mock
    private MutualFundMap mutualFundMap;

    @Mock
    private ConsolePrinter printer;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.initMocks(this);
        addStockCommand = new AddStockCommand(mutualFundMap, printer);
    }


    @Test
    void shouldAddGivenStockToMutualFund() {
        Command command = new Command(CommandType.ADD_STOCK, List.of("fundName", "stockName"));
        addStockCommand.execute(command);
        verify(mutualFundMap, times(1)).addStockToMutualFund("fundName", "stockName");
        verifyNoMoreInteractions(mutualFundMap);
        verifyZeroInteractions(printer);
    }

    @Test
    void shouldPrintsErrorMessageWhenFundNotFound() {
        Command command = new Command(CommandType.ADD_STOCK, List.of("fundName", "stockName"));
        doThrow(new FundNotFoundException()).when(mutualFundMap).addStockToMutualFund("fundName", "stockName");
        addStockCommand.execute(command);
        verify(mutualFundMap, times(1)).addStockToMutualFund("fundName", "stockName");
        verify(printer, times(1)).print("FUND_NOT_FOUND");
        verifyNoMoreInteractions(mutualFundMap);
        verifyNoMoreInteractions(printer);
    }
}