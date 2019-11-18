package com.godev.budgetgo.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "operations_key_sequence")
public class OperationsKeySequence {

    @Id
    @Column(name = "storage_id")
    private Long storageId;

    @Column(name = "next_operation_id")
    private Long nextOperationId;

    public Long getStorageId() {
        return storageId;
    }

    public void setStorageId(Long storageId) {
        this.storageId = storageId;
    }

    public Long getNextOperationId() {
        return nextOperationId;
    }

    public void setNextOperationId(Long nextOperationId) {
        this.nextOperationId = nextOperationId;
    }
}
