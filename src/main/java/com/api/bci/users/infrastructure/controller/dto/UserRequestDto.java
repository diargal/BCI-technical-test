package com.api.bci.users.infrastructure.controller.dto;

import lombok.AllArgsConstructor;
import lombok.Data;

import java.util.List;

@Data
@AllArgsConstructor
public class UserRequestDto {

    private String name;
    private String email;
    private String password;
    private List<PhoneDto> phones;
    private boolean isActive;
}
