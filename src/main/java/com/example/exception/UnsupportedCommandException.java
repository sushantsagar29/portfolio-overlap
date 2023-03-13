package com.example.exception;

import com.example.constants.Constants;

public class UnsupportedCommandException extends RuntimeException {
    public UnsupportedCommandException(String input) {
        super(String.format("%s : %s", Constants.COMMAND_TYPE_NOT_SUPPORTED, input));
    }
}