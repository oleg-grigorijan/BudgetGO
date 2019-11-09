package com.godev.budgetgo.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.godev.budgetgo.entity.Currency;
import com.godev.budgetgo.validation.constraint.NullOrNotBlank;

import javax.validation.constraints.Size;

public class CurrencyPatchesDto {

    @NullOrNotBlank
    @Size(max = Currency.NAME_MAX_LENGTH)
    private String name;

    @NullOrNotBlank
    @Size(min = Currency.ISO_CODE_LENGTH, max = Currency.ISO_CODE_LENGTH)
    private String isoCode;

    @NullOrNotBlank
    @Size(max = Currency.SYMBOL_MAX_LENGTH)
    private String symbol;

    @JsonProperty("isSymbolPrefixed")
    private Boolean symbolPrefixed;

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

    public String getSymbol() {
        return symbol;
    }

    public void setSymbol(String symbol) {
        this.symbol = symbol;
    }

    public Boolean getSymbolPrefixed() {
        return symbolPrefixed;
    }

    public void setSymbolPrefixed(Boolean symbolPrefixed) {
        this.symbolPrefixed = symbolPrefixed;
    }
}
