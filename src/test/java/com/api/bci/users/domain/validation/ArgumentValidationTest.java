package com.api.bci.users.domain.validation;

import com.api.bci.users.domain.validation.exception.EmailFormatException;
import com.api.bci.users.domain.validation.exception.RequiredValueNoNullOrNoEmptyException;
import org.junit.jupiter.api.Test;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

class ArgumentValidationTest {

    @Test
    void testEmailFormatValidation_ValidEmail_NoExceptionThrown() {
        assertDoesNotThrow(() -> ArgumentValidation.emailFormatValidation("john.doe@example.com", "email"));
    }

    @Test
    void testEmailFormatValidation_InvalidEmail_ExceptionThrown() {
        EmailFormatException exception = assertThrows(EmailFormatException.class,
                () -> ArgumentValidation.emailFormatValidation("invalid-email", "email"));
        assertEquals("No se cumple con el formato de correo.", exception.getMessage());
    }

    @Test
    void testValueNotNullOrNotEmptyValidation_NonEmptyString_NoExceptionThrown() {
        assertDoesNotThrow(() -> ArgumentValidation.valueNotNullOrNotEmptyValidation("non-empty", "name"));
    }

    @Test
    void testValueNotNullOrNotEmptyValidation_NullString_ExceptionThrown() {
        RequiredValueNoNullOrNoEmptyException exception = assertThrows(RequiredValueNoNullOrNoEmptyException.class,
                () -> ArgumentValidation.valueNotNullOrNotEmptyValidation(null, "name"));
        assertEquals("Valor vacío o nulo: name", exception.getMessage());
    }

    @Test
    void testValueNotNullOrNotEmptyValidation_EmptyString_ExceptionThrown() {
        RequiredValueNoNullOrNoEmptyException exception = assertThrows(RequiredValueNoNullOrNoEmptyException.class,
                () -> ArgumentValidation.valueNotNullOrNotEmptyValidation("", "name"));
        assertEquals("Valor vacío o nulo: name", exception.getMessage());
    }

    @Test
    void testListNotNullOrNotEmptyValidation_NonEmptyList_NoExceptionThrown() {
        List<String> nonEmptyList = Arrays.asList("item1", "item2");
        assertDoesNotThrow(() -> ArgumentValidation.listNotNullOrNotEmptyValidation(nonEmptyList, "list"));
    }

    @Test
    void testListNotNullOrNotEmptyValidation_NullList_ExceptionThrown() {
        RequiredValueNoNullOrNoEmptyException exception = assertThrows(RequiredValueNoNullOrNoEmptyException.class,
                () -> ArgumentValidation.listNotNullOrNotEmptyValidation(null, "list"));
        assertEquals("Valor vacío o nulo: list", exception.getMessage());
    }

    @Test
    void testListNotNullOrNotEmptyValidation_EmptyList_ExceptionThrown() {
        RequiredValueNoNullOrNoEmptyException exception = assertThrows(RequiredValueNoNullOrNoEmptyException.class,
                () -> ArgumentValidation.listNotNullOrNotEmptyValidation(Collections.emptyList(), "list"));
        assertEquals("Valor vacío o nulo: list", exception.getMessage());
    }
}
