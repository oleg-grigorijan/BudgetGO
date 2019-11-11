package com.godev.budgetgo.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.godev.budgetgo.entity.Currency;
import com.godev.budgetgo.validation.constraint.NullOrNotBlank;

import javax.validation.constraints.Size;
import java.util.Optional;

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

    public Optional<String> getName() {
        return Optional.ofNullable(name);
    }

    public void setName(String name) {
        this.name = name;
    }

    public Optional<String> getIsoCode() {
        return Optional.ofNullable(isoCode);
    }

    public void setIsoCode(String isoCode) {
        this.isoCode = isoCode;
    }

    public Optional<String> getSymbol() {
        return Optional.ofNullable(symbol);
    }

    public void setSymbol(String symbol) {
        this.symbol = symbol;
    }

    public Optional<Boolean> getSymbolPrefixed() {
        return Optional.ofNullable(symbolPrefixed);
    }

    public void setSymbolPrefixed(Boolean symbolPrefixed) {
        this.symbolPrefixed = symbolPrefixed;
    }
}
