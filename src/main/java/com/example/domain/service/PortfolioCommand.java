package com.example.domain.service;

import com.example.infra.model.Command;

public interface PortfolioCommand {
    void execute(Command command);
}
