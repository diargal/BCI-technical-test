package com.api.bci.users.domain.validation.exception;

public class ExistEmailException extends RuntimeException {
    private static final long serialVersionUID = 1L;

    public ExistEmailException(String message) {
        super(message);
    }
}
