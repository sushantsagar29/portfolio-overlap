package com.example.exception;

public class MalformedInputFileException extends RuntimeException {
    public MalformedInputFileException(String errorMessage) {
        super(errorMessage);
    }
}