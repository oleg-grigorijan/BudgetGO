package com.godev.budgetgo.entity;

import javax.persistence.*;
import java.sql.Date;
import java.util.Objects;

@Entity
@Table(name = "operations")
public class Operation {
    @Id
    @GeneratedValue
    private Long id;

    @ManyToOne
    @JoinColumn(name = "storage_id", nullable = false)
    private Storage storage;

    @Column(name = "money_delta", nullable = false)
    private long moneyDelta;

    @ManyToOne
    @JoinColumn(name = "category_id", nullable = false)
    private Category category;

    @Column(nullable = false)
    private Date date;

    @Column(nullable = false)
    private String description;

    @Column(name = "creation_date", nullable = false)
    private Date creationDate;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Storage getStorage() {
        return storage;
    }

    public void setStorage(Storage storage) {
        this.storage = storage;
    }

    public long getMoneyDelta() {
        return moneyDelta;
    }

    public void setMoneyDelta(long moneyDelta) {
        this.moneyDelta = moneyDelta;
    }

    public Category getCategory() {
        return category;
    }

    public void setCategory(Category category) {
        this.category = category;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Date getCreationDate() {
        return creationDate;
    }

    public void setCreationDate(Date creationDate) {
        this.creationDate = creationDate;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Operation)) return false;
        Operation operation = (Operation) o;
        return moneyDelta == operation.moneyDelta &&
                id.equals(operation.id) &&
                storage.equals(operation.storage) &&
                category.equals(operation.category) &&
                date.equals(operation.date) &&
                description.equals(operation.description) &&
                creationDate.equals(operation.creationDate);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, storage, moneyDelta, category, date, description, creationDate);
    }

    @Override
    public String toString() {
        return "Operation{" +
                "id=" + id +
                ", storage=" + storage +
                ", moneyDelta=" + moneyDelta +
                ", category=" + category +
                ", date=" + date +
                ", description='" + description + '\'' +
                ", creationDate=" + creationDate +
                '}';
    }
}
