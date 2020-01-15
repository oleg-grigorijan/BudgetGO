package com.godev.budgetgo.entity;

import javax.persistence.Embeddable;
import java.io.Serializable;
import java.util.Objects;

@Embeddable
public class UserStorageKey implements Serializable {

    private Long userId;

    private Long storageId;

    public UserStorageKey() {
    }

    public UserStorageKey(Long userId, Long storageId) {
        this.userId = userId;
        this.storageId = storageId;
    }

    public Long getUserId() {
        return userId;
    }

    public void setUserId(Long userId) {
        this.userId = userId;
    }

    public Long getStorageId() {
        return storageId;
    }

    public void setStorageId(Long storageId) {
        this.storageId = storageId;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof UserStorageKey)) return false;
        UserStorageKey that = (UserStorageKey) o;
        return userId.equals(that.userId) &&
                storageId.equals(that.storageId);
    }

    @Override
    public int hashCode() {
        return Objects.hash(userId, storageId);
    }

    @Override
    public String toString() {
        return "UserStorageKey{" +
                "userId=" + userId +
                ", storageId=" + storageId +
                '}';
    }
}
