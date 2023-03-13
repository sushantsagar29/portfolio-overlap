package com.example.exception;

import com.example.constants.Constants;

public class FundNotFoundException extends RuntimeException {
    public FundNotFoundException() {
        super(Constants.FUND_NOT_FOUND);
    }
}