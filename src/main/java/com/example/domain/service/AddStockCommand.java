package com.example.domain.service;

import com.example.constants.Constants;
import com.example.domain.MutualFundMap;
import com.example.infra.model.Command;

import java.util.List;

public class AddStockCommand implements PortfolioCommand {

    private final MutualFundMap mutualFundMap;

    public AddStockCommand(MutualFundMap mutualFundMap) {
        this.mutualFundMap = mutualFundMap;
    }

    @Override
    public void execute(Command command) {
        List<String> arguments = command.getArguments();
        String fundName = arguments.get(0);
        String stockName = String.join(Constants.SPACE, arguments.subList(1, arguments.size()));
        mutualFundMap.addStockToMutualFund(fundName, stockName);
    }
}
