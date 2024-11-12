package com.api.bci.users.domain.model;

import com.api.bci.users.domain.validation.ArgumentValidation;
import lombok.Getter;

@Getter
public class Phone {
    private String number;
    private String cityCode;
    private String countryCode;

    public Phone(String number, String cityCode, String countryCode) {
        setNumber(number);
        setCityCode(cityCode);
        setCountryCode(countryCode);
    }

    public void setNumber(String number) {
        ArgumentValidation.valueNotNullOrNotEmptyValidation(number, "number");
        this.number = number;
    }

    public void setCityCode(String cityCode) {
        ArgumentValidation.valueNotNullOrNotEmptyValidation(number, "cityCode");
        this.cityCode = cityCode;
    }

    public void setCountryCode(String countryCode) {
        ArgumentValidation.valueNotNullOrNotEmptyValidation(number, "countryCode");
        this.countryCode = countryCode;
    }
}
