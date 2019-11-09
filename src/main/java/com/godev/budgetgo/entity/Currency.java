package com.godev.budgetgo.entity;

import javax.persistence.*;
import java.util.Objects;

@Entity
@Table(name = "currencies")
public class Currency implements Cloneable {

    public static final int NAME_MAX_LENGTH = 255;
    public static final int ISO_CODE_LENGTH = 3;
    public static final int SYMBOL_MAX_LENGTH = 3;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false, length = NAME_MAX_LENGTH)
    private String name;

    @Column(name = "iso_code", nullable = false, length = ISO_CODE_LENGTH)
    private String isoCode;

    @Column(nullable = false, length = SYMBOL_MAX_LENGTH)
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

    /**
     * @return Shallow clone of instance
     */
    @Override
    public Currency clone() {
        try {
            return (Currency) super.clone();
        } catch (CloneNotSupportedException e) {
            e.printStackTrace();
            throw new RuntimeException(e);
        }
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
