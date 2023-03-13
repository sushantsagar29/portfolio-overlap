package com.example.domain;

import com.example.exception.FundNotFoundException;
import com.example.infra.model.FundStore;

import java.util.HashMap;
import java.util.Objects;
import java.util.Set;

public class MutualFundMap extends HashMap<String, Set<String>> {
    public MutualFundMap(FundStore fundStore) {
        fundStore.getFunds().forEach(mutualFund -> this.put(mutualFund.getName(), mutualFund.getStocks()));
    }

    public Set<String> getStocksInMutualFund(String fundName) {
        Set<String> stockList = this.get(fundName);
        if (Objects.isNull(stockList)) {
            throw new FundNotFoundException();
        }
        return stockList;
    }

    public Set<String> addStockToMutualFund(String fundName, String stockName) {
        Set<String> stockList = getStocksInMutualFund(fundName);
        stockList.add(stockName);
        return stockList;
    }
}
