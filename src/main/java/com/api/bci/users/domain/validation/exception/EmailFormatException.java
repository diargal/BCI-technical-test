package com.api.bci.users.domain.validation.exception;

public class EmailFormatException extends RuntimeException {
    private static final long serialVersionUID = 1L;

    public EmailFormatException(String message) {
        super(message);
    }
}
