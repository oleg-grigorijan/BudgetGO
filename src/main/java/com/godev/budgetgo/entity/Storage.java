package com.godev.budgetgo.entity;

import javax.persistence.*;
import java.util.Objects;

@Entity
@Table(name = "storages")
public class Storage implements Cloneable {

    public static final int NAME_MAX_LENGTH = 255;
    public static final int DESCRIPTION_MAX_LENGTH = 255;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private long balance;

    @Column(nullable = false, length = NAME_MAX_LENGTH)
    private String name;

    @Column(nullable = false, length = DESCRIPTION_MAX_LENGTH)
    private String description;

    @ManyToOne
    @JoinColumn(name = "currency_id", nullable = false)
    private Currency currency;

    @Column(name = "initial_balance", nullable = false)
    private long initialBalance;

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

    public long getInitialBalance() {
        return initialBalance;
    }

    public void setInitialBalance(long initialBalance) {
        this.initialBalance = initialBalance;
    }

    /**
     * @return Shallow clone of instance
     */
    @Override
    public Storage clone() {
        try {
            return (Storage) super.clone();
        } catch (CloneNotSupportedException e) {
            e.printStackTrace();
            throw new RuntimeException(e);
        }
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Storage)) return false;
        Storage storage = (Storage) o;
        return balance == storage.balance &&
                initialBalance == storage.initialBalance &&
                id.equals(storage.id) &&
                name.equals(storage.name) &&
                description.equals(storage.description) &&
                currency.equals(storage.currency);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, balance, name, description, currency, initialBalance);
    }

    @Override
    public String toString() {
        return "Storage{" +
                "id=" + id +
                ", balance=" + balance +
                ", name='" + name + '\'' +
                ", description='" + description + '\'' +
                ", currency=" + currency +
                ", initialBalance=" + initialBalance +
                '}';
    }
}
