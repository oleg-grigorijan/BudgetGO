package com.godev.budgetgo.entity;

import javax.persistence.*;
import java.util.Objects;

@Entity
@Table(name = "currencies")
public class Currency {
    @Id
    @GeneratedValue
    private Long id;

    @Column(nullable = false)
    private String name;

    @Column(name = "iso_code", nullable = false)
    private String isoCode;

    @Column(nullable = false)
    private String symbol;

    @Column(name = "is_symbol_prefixed", nullable = false)
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

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Currency)) return false;
        Currency currency = (Currency) o;
        return symbolPrefixed == currency.symbolPrefixed &&
                id.equals(currency.id) &&
                name.equals(currency.name) &&
                isoCode.equals(currency.isoCode) &&
                symbol.equals(currency.symbol);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, name, isoCode, symbol, symbolPrefixed);
    }

    @Override
    public String toString() {
        return "Currency{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", isoCode='" + isoCode + '\'' +
                ", symbol='" + symbol + '\'' +
                ", symbolPrefixed=" + symbolPrefixed +
                '}';
    }
}
