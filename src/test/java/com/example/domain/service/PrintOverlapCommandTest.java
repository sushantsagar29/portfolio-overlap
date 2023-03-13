package com.example.domain.service;

import com.example.domain.MutualFundMap;
import com.example.domain.Portfolio;
import com.example.exception.FundNotFoundException;
import com.example.infra.model.Command;
import com.example.infra.model.CommandType;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.List;

import static com.example.domain.TestFixtures.stocks1;
import static com.example.domain.TestFixtures.stocks2;
import static com.example.domain.TestFixtures.stocks3;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.verifyNoMoreInteractions;
import static org.mockito.Mockito.verifyZeroInteractions;
import static org.mockito.Mockito.anyString;
import static org.mockito.Mockito.when;

class PrintOverlapCommandTest {

    private PrintOverlapCommand printOverlapCommand;

    @Mock
    private Portfolio portfolio;

    @Mock
    private MutualFundMap mutualFundMap;

    @Mock
    private ConsolePrinter printer;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.initMocks(this);
        printOverlapCommand = new PrintOverlapCommand(portfolio, mutualFundMap, printer);
    }

    @Test
    void shouldNotPrintAnythingIfOverlapPercentagesIsZero() {
        when(portfolio.getMutualFunds()).thenReturn(List.of("Fund1"));
        when(mutualFundMap.getStocksInMutualFund("Fund1")).thenReturn(stocks1);

        when(mutualFundMap.getStocksInMutualFund("Fund2")).thenReturn(stocks2);

        Command command = new Command(CommandType.CALCULATE_OVERLAP, List.of("Fund2"));
        printOverlapCommand.execute(command);

        verify(mutualFundMap, times(2)).getStocksInMutualFund(anyString());
        verify(portfolio, times(1)).getMutualFunds();
        verify(printer, times(0)).print(anyString());
        verifyNoMoreInteractions(mutualFundMap, portfolio, printer);
    }

    @Test
    void shouldPrintOverlapPercentagesWhenNonZero() {
        when(portfolio.getMutualFunds()).thenReturn(List.of("Fund1"));
        when(mutualFundMap.getStocksInMutualFund("Fund1")).thenReturn(stocks1);

        when(mutualFundMap.getStocksInMutualFund("Fund3")).thenReturn(stocks3);

        Command command = new Command(CommandType.CALCULATE_OVERLAP, List.of("Fund3"));
        printOverlapCommand.execute(command);

        verify(mutualFundMap, times(2)).getStocksInMutualFund(anyString());
        verify(portfolio, times(1)).getMutualFunds();
        verify(printer, times(1)).print("Fund3 Fund1 50.00%");
        verifyNoMoreInteractions(mutualFundMap, portfolio, printer);
    }

    @Test
    void shouldPrintsErrorMessageWhenFundNotFound() {
        when(mutualFundMap.getStocksInMutualFund("UnknownFund")).thenThrow(new FundNotFoundException());

        Command command = new Command(CommandType.CALCULATE_OVERLAP, List.of("UnknownFund"));
        printOverlapCommand.execute(command);

        verify(mutualFundMap, times(1)).getStocksInMutualFund("UnknownFund");
        verify(printer, times(1)).print("FUND_NOT_FOUND");
        verifyNoMoreInteractions(mutualFundMap, printer);
        verifyZeroInteractions(portfolio);
    }

}