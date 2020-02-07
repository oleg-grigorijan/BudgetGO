package com.godev.budgetgo.entity;

import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Embeddable;
import java.io.Serializable;

@Embeddable
@Data
@NoArgsConstructor
public class StorageOperationKey implements Serializable {

    private Long storageId;

    private Long operationId;

    public StorageOperationKey(Long storageId, Long operationId) {
        this.storageId = storageId;
        this.operationId = operationId;
    }
}
