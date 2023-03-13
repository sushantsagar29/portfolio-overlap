package com.example.domain;

import java.text.DecimalFormat;
import java.util.*;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class MutualFund {
    private final String name;
    private final Set<String> stocks;

    public MutualFund(String name, Set<String> stocks) {
        this.name = name;
        this.stocks = stocks;
    }

    public String getName() {
        return name;
    }

    public Set<String> getStocks() {
        return stocks;
    }

    public String calculateOverLapWith(MutualFund fund) {
        Set<String> thisStockList = this.getStocks();
        Set<String> toCompareStockList = fund.getStocks();

        Set<String> uniqueStockList =
                Stream.of(thisStockList, toCompareStockList)
                .flatMap(Collection::stream)
                .collect(Collectors.toSet());

        int totalStocksCount = thisStockList.size() + toCompareStockList.size();
        int commonStocksCount = totalStocksCount - uniqueStockList.size();

        DecimalFormat df = new DecimalFormat("#.##");
        df.setMinimumFractionDigits(2);

        Double overlap = (200 * (double) commonStocksCount / totalStocksCount);
        return df.format(overlap);
    }
}
