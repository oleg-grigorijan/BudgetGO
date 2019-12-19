package com.godev.budgetgo.dto;

import com.godev.budgetgo.entity.Currency;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;

public class CurrencyCreationDto {

    @NotBlank
    @Size(max = Currency.NAME_MAX_LENGTH)
    private String name;

    @NotBlank
    @Size(min = Currency.ISO_CODE_LENGTH, max = Currency.ISO_CODE_LENGTH)
    private String isoCode;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getIsoCode() {
        return isoCode;
    }

    public void setIsoCode(String isoCode) {
        this.isoCode = isoCode;
    }
}
