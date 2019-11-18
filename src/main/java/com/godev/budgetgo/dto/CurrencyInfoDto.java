package com.godev.budgetgo.dto;

import com.fasterxml.jackson.annotation.JsonProperty;

public class CurrencyInfoDto {

    private Long id;

    private String name;

    private String isoCode;

    private String symbol;

    @JsonProperty("isSymbolPrefixed")
    private boolean symbolPrefixed;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

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

    public boolean isSymbolPrefixed() {
        return symbolPrefixed;
    }

    public void setSymbolPrefixed(boolean symbolPrefixed) {
        this.symbolPrefixed = symbolPrefixed;
    }
}
