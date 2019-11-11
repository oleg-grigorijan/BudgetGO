package com.godev.budgetgo.entity;

import javax.persistence.Embeddable;
import java.io.Serializable;
import java.util.Objects;

@Embeddable
public class StorageOperationKey implements Serializable {

    private Long storageId;

    private Long operationId;

    public StorageOperationKey() {
    }

    public StorageOperationKey(Long storageId, Long operationId) {
        this.storageId = storageId;
        this.operationId = operationId;
    }

    public Long getStorageId() {
        return storageId;
    }

    public void setStorageId(Long storageId) {
        this.storageId = storageId;
    }

    public Long getOperationId() {
        return operationId;
    }

    public void setOperationId(Long operationId) {
        this.operationId = operationId;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof StorageOperationKey)) return false;
        StorageOperationKey that = (StorageOperationKey) o;
        return storageId.equals(that.storageId) &&
                operationId.equals(that.operationId);
    }

    @Override
    public int hashCode() {
        return Objects.hash(storageId, operationId);
    }

    @Override
    public String toString() {
        return "StorageOperationKey{" +
                "storageId=" + storageId +
                ", operationId=" + operationId +
                '}';
    }
}
