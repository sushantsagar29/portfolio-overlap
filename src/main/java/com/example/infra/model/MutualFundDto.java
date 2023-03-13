package com.example.infra.model;

import java.util.Set;

public class MutualFundDto {
    private String name;
    private Set<String> stocks;

    public String getName() {
        return name;
    }

    public Set<String> getStocks() {
        return stocks;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setStocks(Set<String> stocks) {
        this.stocks = stocks;
    }
}
