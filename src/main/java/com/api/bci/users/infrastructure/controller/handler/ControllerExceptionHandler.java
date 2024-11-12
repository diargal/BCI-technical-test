package com.api.bci.users.infrastructure.controller.handler;

import com.api.bci.users.domain.validation.exception.EmailFormatException;
import com.api.bci.users.domain.validation.exception.ExistEmailException;
import com.api.bci.users.domain.validation.exception.RequiredValueNoNullOrNoEmptyException;
import com.api.bci.users.domain.validation.exception.UserNotFoundException;
import com.api.bci.users.infrastructure.controller.dto.ErrorResponseDto;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.security.core.AuthenticationException;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;

@ControllerAdvice
public class ControllerExceptionHandler {

    @ExceptionHandler({RequiredValueNoNullOrNoEmptyException.class, EmailFormatException.class})
    private ResponseEntity<ErrorResponseDto> sendResponse(Exception exception) {
        ErrorResponseDto errorResponseDto = new ErrorResponseDto(exception.getMessage());
        if (exception instanceof RequiredValueNoNullOrNoEmptyException ||
                exception instanceof EmailFormatException) {
            return new ResponseEntity<>(errorResponseDto, HttpStatus.BAD_REQUEST);
        }
        return new ResponseEntity<>(errorResponseDto, HttpStatus.INTERNAL_SERVER_ERROR);
    }

    @ExceptionHandler(ExistEmailException.class)
    public ResponseEntity<ErrorResponseDto> handleEmailFormatException(ExistEmailException exception) {
        ErrorResponseDto errorResponseDto = new ErrorResponseDto(exception.getMessage());
        return new ResponseEntity<>(errorResponseDto, HttpStatus.CONFLICT);
    }

    @ExceptionHandler(UserNotFoundException.class)
    public ResponseEntity<ErrorResponseDto> handleUserNotFoundException(UserNotFoundException exception) {
        ErrorResponseDto errorResponseDto = new ErrorResponseDto(exception.getMessage());
        return new ResponseEntity<>(errorResponseDto, HttpStatus.NOT_FOUND);
    }

    @ExceptionHandler(Exception.class)
    public ResponseEntity<ErrorResponseDto> handleGenericException(Exception exception) {
        ErrorResponseDto errorResponseDto = new ErrorResponseDto(exception.getMessage());
        return new ResponseEntity<>(errorResponseDto, HttpStatus.INTERNAL_SERVER_ERROR);
    }
}
