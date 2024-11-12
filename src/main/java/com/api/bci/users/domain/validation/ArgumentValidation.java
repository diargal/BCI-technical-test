package com.api.bci.users.domain.validation;

import com.api.bci.users.domain.model.enums.ErrorMessageEnum;
import com.api.bci.users.domain.validation.exception.EmailFormatException;
import com.api.bci.users.domain.validation.exception.RequiredValueNoNullOrNoEmptyException;

import java.util.List;
import java.util.Objects;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class ArgumentValidation {
    private static final String EMAIL_REGEX =
            "^[a-zA-Z0-9_+&*-]+(?:\\.[a-zA-Z0-9_+&*-]+)*@(?:[a-zA-Z0-9-]+\\.)+[a-zA-Z]{2,7}$";
    private static final Pattern pattern = Pattern.compile(EMAIL_REGEX);

    private ArgumentValidation() {
    }

    public static void emailFormatValidation(String email, String fieldName) {
        String initialMessage = ErrorMessageEnum.EMAIL_FORMAT_EXCEPTION.getMessage();
        Matcher matcher = pattern.matcher(email);
        if (!matcher.matches())
            throw new EmailFormatException(initialMessage);
    }

    public static void valueNotNullOrNotEmptyValidation(String value, String fieldName) {
        String initialMessage = ErrorMessageEnum.VALUE_NULL_OR_EMPTY_EXCEPTION.getMessage();
        if (Objects.isNull(value) || value.isEmpty()) {
            throw new RequiredValueNoNullOrNoEmptyException(initialMessage.concat(fieldName));
        }
    }

    public static void listNotNullOrNotEmptyValidation(List<?> values, String fieldName) {
        String initialMessage = ErrorMessageEnum.VALUE_NULL_OR_EMPTY_EXCEPTION.getMessage();
        if (Objects.isNull(values) || values.isEmpty()) {
            throw new RequiredValueNoNullOrNoEmptyException(initialMessage.concat(fieldName));
        }
    }

}
