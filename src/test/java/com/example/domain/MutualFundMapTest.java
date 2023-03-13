package com.example.domain;

import com.example.exception.FundNotFoundException;
import com.example.infra.model.FundStore;
import com.example.infra.model.MutualFundDto;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;

import static com.example.domain.TestFixtures.*;

class MutualFundMapTest {

    MutualFundMap mutualFundMap;


    @BeforeEach
    void setUp() {
        FundStore fundStore = new FundStore();
        MutualFundDto mutualFundDto1 = new MutualFundDto();
        mutualFundDto1.setName("fund1");
        mutualFundDto1.setStocks(stocks1);
        MutualFundDto mutualFundDto2 = new MutualFundDto();
        mutualFundDto2.setName("fund2");
        mutualFundDto2.setStocks(stocks2);
        fundStore.setFunds(Arrays.asList(mutualFundDto1, mutualFundDto2));

        mutualFundMap = new MutualFundMap(fundStore);
    }

    @Test
    void shouldReturnStocksInGivenMutualFund() {
        Set<String> expected = new HashSet<>(Arrays.asList(
                "stock1",
                "stock2"
        ));

        Set<String> actual = mutualFundMap.getStocksInMutualFund("fund1");
        Assertions.assertEquals(expected, actual);
    }

    @Test
    void shouldThrowErrorWhenMutualFundNotFound() {
        Assertions
                .assertThrows(FundNotFoundException.class,
                        () -> mutualFundMap.getStocksInMutualFund("invalidFundName"),
                        "FundNotFoundException error is expected for invalidFundName");
    }

    @Test
    void shouldAddStockToGivenMutualFund() {
        Set<String> expected = stocks2;
        expected.add("stock10");

        Set<String> actual = mutualFundMap.addStockToMutualFund("fund2", "stock10");
        Assertions.assertEquals(expected, actual);
    }

    @Test
    void ThrowErrorWhenMutualFundNotFoundWhenAddingStocks() {
        Assertions
                .assertThrows(FundNotFoundException.class,
                        () -> mutualFundMap.addStockToMutualFund("invalidFundName", "stock10"),
                        "FundNotFoundException error is expected for invalidFundName");
    }
}