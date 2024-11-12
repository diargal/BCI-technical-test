package com.api.bci.users.domain.model;

import com.api.bci.users.domain.validation.ArgumentValidation;
import com.api.bci.users.infrastructure.adapter.security.model.enums.RoleEnum;
import lombok.Data;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Set;
import java.util.UUID;

@Data
public class UserRequest {

    private UUID id;
    private String name;
    private String email;
    private String password;
    private List<Phone> phones;
    private LocalDateTime created;
    private LocalDateTime modified;
    private LocalDateTime lastLogin;
    private boolean isactive;
    private Set<RoleEnum> roles;

    public UserRequest(String name, String email, String password, List<Phone> phones) {
        setName(name);
        setEmail(email);
        setPassword(password);
        setPhones(phones);
    }

    public void setName(String name) {
        ArgumentValidation.valueNotNullOrNotEmptyValidation(name, "name");
        this.name = name;
    }

    public void setEmail(String email) {
        ArgumentValidation.valueNotNullOrNotEmptyValidation(email, "email");
        ArgumentValidation.emailFormatValidation(email, "email");
        this.email = email;
    }

    public void setPassword(String password) {
        ArgumentValidation.valueNotNullOrNotEmptyValidation(password, "password");
        this.password = password;
    }

    public void setPhones(List<Phone> phones) {
        ArgumentValidation.listNotNullOrNotEmptyValidation(phones, "phones");
        this.phones = phones;
    }
}
