package com.api.bci.users.domain.model.enums;

import lombok.Getter;

//@AllArgsConstructor
@Getter
public enum ErrorMessageEnum {
    EMAIL_EXIST_EXCEPTION("El email ya se encuentra registrado."),
    EMAIL_FORMAT_EXCEPTION("No se cumple con el formato de correo: "),
    USER_NOT_FOUND_EXCEPTION("No se encontró registro."),
    VALUE_NULL_OR_EMPTY_EXCEPTION("Valor vacío o nulo: ");
    private final String message;

    ErrorMessageEnum(String message) {
        this.message = message;
    }
}
