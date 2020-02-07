package com.godev.budgetgo.entity;

import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@Entity
@Table(name = "storages")
@Data
@NoArgsConstructor
public class Storage {

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

    public Storage cloneShallow() {
        Storage e = new Storage();
        e.setId(id);
        e.setBalance(balance);
        e.setName(name);
        e.setDescription(description);
        e.setCurrency(currency);
        e.setInitialBalance(initialBalance);
        return e;
    }
}
