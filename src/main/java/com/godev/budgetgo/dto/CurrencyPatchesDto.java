package com.godev.budgetgo.dto;

import com.fasterxml.jackson.annotation.JsonProperty;

public class CurrencyPatchesDto {
    private String name;
    private String isoCode;
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
