package com.godev.budgetgo.entity;

import javax.persistence.Column;
import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.MapsId;
import javax.persistence.Table;
import java.time.LocalDate;
import java.util.Objects;

@Entity
@Table(name = "operations")
public class Operation implements Cloneable {

    public static final int DESCRIPTION_MAX_LENGTH = 255;

    @EmbeddedId
    private StorageOperationKey id;

    @ManyToOne(fetch = FetchType.LAZY)
    @MapsId("storageId")
    @JoinColumn(name = "storage_id", nullable = false)
    private Storage storage;

    @Column(name = "money_delta", nullable = false)
    private long moneyDelta;

    @ManyToOne
    @JoinColumn(name = "category_id", nullable = false)
    private Category category;

    @Column(nullable = false)
    private LocalDate date;

    @Column(nullable = false, length = DESCRIPTION_MAX_LENGTH)
    private String description;

    @Column(name = "date_created", nullable = false)
    private LocalDate dateCreated;

    @Column(name = "date_modified", nullable = false)
    private LocalDate dateModified;

    @ManyToOne
    @JoinColumn(name = "creator_id", nullable = false)
    private User creator;

    @ManyToOne
    @JoinColumn(name = "last_editor_id", nullable = false)
    private User lastEditor;

    public StorageOperationKey getId() {
        return id;
    }

    public void setId(StorageOperationKey id) {
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

    public LocalDate getDate() {
        return date;
    }

    public void setDate(LocalDate date) {
        this.date = date;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public LocalDate getDateCreated() {
        return dateCreated;
    }

    public void setDateCreated(LocalDate creationDate) {
        this.dateCreated = creationDate;
    }

    public LocalDate getDateModified() {
        return dateModified;
    }

    public void setDateModified(LocalDate dateModified) {
        this.dateModified = dateModified;
    }

    public User getLastEditor() {
        return lastEditor;
    }

    public void setLastEditor(User lastEditor) {
        this.lastEditor = lastEditor;
    }

    public User getCreator() {
        return creator;
    }

    public void setCreator(User creator) {
        this.creator = creator;
    }

    /**
     * @return Shallow clone of instance
     */
    @Override
    public Operation clone() {
        try {
            return (Operation) super.clone();
        } catch (CloneNotSupportedException e) {
            e.printStackTrace();
            throw new RuntimeException(e);
        }
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
                dateCreated.equals(operation.dateCreated) &&
                dateModified.equals(operation.dateModified) &&
                creator.equals(operation.creator) &&
                lastEditor.equals(operation.lastEditor);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, storage, moneyDelta, category, date, description, dateCreated, dateModified, creator, lastEditor);
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
                ", dateCreated=" + dateCreated +
                ", dateModified=" + dateModified +
                ", creator=" + creator +
                ", lastEditor=" + lastEditor +
                '}';
    }
}
