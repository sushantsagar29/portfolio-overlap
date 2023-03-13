package com.example.domain.service;

import com.example.exception.MethodNotFoundException;
import com.example.infra.model.Command;

public class UnsupportedCommand implements PortfolioCommand{

    @Override
    public void execute(Command command) {
        throw new MethodNotFoundException();
    }
}
