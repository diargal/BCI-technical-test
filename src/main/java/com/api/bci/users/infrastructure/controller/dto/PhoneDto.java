package com.api.bci.users.infrastructure.controller.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class PhoneDto {
    private String number;
    private String cityCode;
    private String countryCode;
}
