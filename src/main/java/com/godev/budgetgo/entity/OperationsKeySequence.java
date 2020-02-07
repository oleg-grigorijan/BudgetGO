package com.godev.budgetgo.entity;

import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "operations_key_sequence")
@Data
@NoArgsConstructor
public class OperationsKeySequence {

    @Id
    @Column(name = "storage_id")
    private Long storageId;

    @Column(name = "next_operation_id")
    private Long nextOperationId;

    public OperationsKeySequence(Long storageId, Long nextOperationId) {
        this.storageId = storageId;
        this.nextOperationId = nextOperationId;
    }
}
