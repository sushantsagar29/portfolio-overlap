package com.example.exception;

public class MethodNotFoundException extends RuntimeException {
    public MethodNotFoundException() {
        super("No method found for the given command type");
    }
}