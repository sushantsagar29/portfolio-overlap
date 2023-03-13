package com.example.domain.service;

import com.example.domain.MutualFund;
import com.example.domain.MutualFundMap;
import com.example.domain.Portfolio;
import com.example.exception.FundNotFoundException;
import com.example.infra.model.Command;

import java.util.List;

public class PrintOverlapCommand implements PortfolioCommand {
    private final Portfolio portfolio;
    private final MutualFundMap mutualFundMap;
    private final ConsolePrinter printer;

    public PrintOverlapCommand(Portfolio portfolio, MutualFundMap mutualFundMap, ConsolePrinter printer) {
        this.portfolio = portfolio;
        this.mutualFundMap = mutualFundMap;
        this.printer = printer;
    }

    @Override
    public void execute(Command command) {
        List<String> arguments = command.getArguments();
        String fundNameToCompare = arguments.get(0);

        try {
            MutualFund fundToCompare = new MutualFund(
                    fundNameToCompare,
                    mutualFundMap.getStocksInMutualFund(fundNameToCompare)
            );

            portfolio.getMutualFunds().forEach(
                    fundName -> {
                        MutualFund mutualFund = new MutualFund(fundName, mutualFundMap.getStocksInMutualFund(fundName));

                        String overlapPercent = mutualFund.calculateOverLapWith(fundToCompare);

                        if (Double.parseDouble(overlapPercent) > 0){
                            printer.print(String.format("%s %s %s %%", fundToCompare.getName(), mutualFund.getName(), overlapPercent));
                        }
                    });
        } catch (FundNotFoundException exception) {
            printer.print(exception.getMessage());
        }
    }
}
