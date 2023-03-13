package com.example.exception;

public class FundStoreMappingException extends RuntimeException {
    public FundStoreMappingException(String errorMessage) {
        super(errorMessage);
    }
}