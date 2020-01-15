package com.godev.budgetgo.entity;

import javax.persistence.Embeddable;
import java.io.Serializable;
import java.util.Objects;

@Embeddable
public class UserCategoryKey implements Serializable {

    private Long userId;

    private Long categoryId;

    public UserCategoryKey() {
    }

    public UserCategoryKey(Long userId, Long categoryId) {
        this.userId = userId;
        this.categoryId = categoryId;
    }

    public Long getUserId() {
        return userId;
    }

    public void setUserId(Long userId) {
        this.userId = userId;
    }

    public Long getCategoryId() {
        return categoryId;
    }

    public void setCategoryId(Long categoryId) {
        this.categoryId = categoryId;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof UserCategoryKey)) return false;
        UserCategoryKey that = (UserCategoryKey) o;
        return userId.equals(that.userId) &&
                categoryId.equals(that.categoryId);
    }

    @Override
    public int hashCode() {
        return Objects.hash(userId, categoryId);
    }

    @Override
    public String toString() {
        return "UserCategoryKey{" +
                "userId=" + userId +
                ", categoryId=" + categoryId +
                '}';
    }
}
