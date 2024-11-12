package com.api.bci.users.domain.validation.exception;

public class RequiredValueNoNullOrNoEmptyException extends RuntimeException {

    private static final long serialVersionUID = 1L;

    public RequiredValueNoNullOrNoEmptyException(String message) {
        super(message);
    }
}
