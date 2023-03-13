package com.example.domain;

import org.junit.jupiter.api.Test;

import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;
import java.util.stream.Collectors;

import static org.junit.jupiter.api.Assertions.*;

class MutualFundTest {

    Set<String> stockList = new HashSet<>(Arrays.asList(
            "INDRAPRASTHA GAS LIMITED",
            "COLGATE - PALMOLIVE (INDIA) LIMITED",
            "INDUS TOWERS LIMITED"));
    MutualFund mutualFund = new MutualFund("ICICI_PRU_NIFTY_NEXT_50_INDEX", stockList);

    @Test
    void shouldReturnNonZeroPercentage() {
        Set<String> stockListToCompare = new HashSet<>(Arrays.asList(
                "COLGATE - PALMOLIVE (INDIA) LIMITED",
                "INDUS TOWERS LIMITED",
                "INTERGLOBE AVIATION LIMITED",
                "MARICO LIMITED"));
        MutualFund mutualFundToCompare = new MutualFund("PARAG_PARIKH_CONSERVATIVE_HYBRID", stockListToCompare);
        String expectedOverlap = "57.14";

        String actualOverlap = mutualFund.calculateOverLapWith(mutualFundToCompare);
        assertEquals(expectedOverlap, actualOverlap);
    }

    @Test
    void shouldReturnZeroPercentage() {
        Set<String> stockListToCompare = new HashSet<>(Arrays.asList(
                "INTERGLOBE AVIATION LIMITED",
                "MARICO LIMITED"));
        MutualFund mutualFundToCompare = new MutualFund("PARAG_PARIKH_CONSERVATIVE_HYBRID", stockListToCompare);
        String expectedOverlap = "0.00";

        String actualOverlap = mutualFund.calculateOverLapWith(mutualFundToCompare);
        assertEquals(expectedOverlap, actualOverlap);
    }
}