package com.godev.budgetgo.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import java.util.Objects;

@Entity
@Table(name = "currencies")
public class Currency implements Cloneable {

    public static final int NAME_MAX_LENGTH = 255;
    public static final int ISO_CODE_LENGTH = 3;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false, length = NAME_MAX_LENGTH)
    private String name;

    @Column(name = "iso_code", nullable = false, length = ISO_CODE_LENGTH)
    private String isoCode;

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
        return id.equals(currency.id) &&
                name.equals(currency.name) &&
                isoCode.equals(currency.isoCode);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, name, isoCode);
    }

    @Override
    public String toString() {
        return "Currency{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", isoCode='" + isoCode + '\'' +
                '}';
    }
}
