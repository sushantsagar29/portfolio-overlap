package com.example.infra.model;

import java.util.List;

public class FundStore {
    private List<MutualFundDto> funds;

    public void setFunds(List<MutualFundDto> funds) {
        this.funds = funds;
    }

    public List<MutualFundDto> getFunds() {
        return funds;
    }
}
