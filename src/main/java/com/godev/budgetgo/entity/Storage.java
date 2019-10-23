package com.godev.budgetgo.entity;

import javax.persistence.*;
import java.util.Objects;

@Entity
@Table(name = "storages")
public class Storage {
    @Id
    @GeneratedValue
    private Long id;

    @Column(nullable = false)
    private long balance;

    @Column(nullable = false)
    private String name;

    @Column(nullable = false)
    private String description;

    @ManyToOne
    @JoinColumn(name = "currency_id", nullable = false)
    private Currency currency;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public long getBalance() {
        return balance;
    }

    public void setBalance(long balance) {
        this.balance = balance;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Currency getCurrency() {
        return currency;
    }

    public void setCurrency(Currency currency) {
        this.currency = currency;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Storage)) return false;
        Storage storage = (Storage) o;
        return balance == storage.balance &&
                id.equals(storage.id) &&
                name.equals(storage.name) &&
                description.equals(storage.description) &&
                currency.equals(storage.currency);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, balance, name, description, currency);
    }

    @Override
    public String toString() {
        return "Storage{" +
                "id=" + id +
                ", balance=" + balance +
                ", name='" + name + '\'' +
                ", description='" + description + '\'' +
                ", currency=" + currency +
                '}';
    }
}
