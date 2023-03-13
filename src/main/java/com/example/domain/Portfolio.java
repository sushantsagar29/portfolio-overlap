package com.example.domain;

import java.util.List;

public class Portfolio {

    private List<String> mutualFunds;

    public void setCurrentPortfolio(List<String> mutualFunds) {
        this.mutualFunds = mutualFunds;
    }

    public List<String> getMutualFunds() {
        return mutualFunds;
    }
}
