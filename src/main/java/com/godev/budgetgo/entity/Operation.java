package com.godev.budgetgo.entity;

import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Column;
import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.MapsId;
import javax.persistence.Table;
import java.time.LocalDate;

@Entity
@Table(name = "operations")
@Data
@NoArgsConstructor
public class Operation {

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

    public Operation cloneShallow() {
        Operation e = new Operation();
        e.setId(id);
        e.setStorage(storage);
        e.setMoneyDelta(moneyDelta);
        e.setCategory(category);
        e.setDate(date);
        e.setDescription(description);
        e.setDateCreated(dateCreated);
        e.setDateModified(dateModified);
        e.setCreator(creator);
        e.setLastEditor(lastEditor);
        return e;
    }
}
