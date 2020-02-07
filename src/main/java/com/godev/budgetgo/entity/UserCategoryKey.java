package com.godev.budgetgo.entity;

import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Embeddable;
import java.io.Serializable;

@Embeddable
@Data
@NoArgsConstructor
public class UserCategoryKey implements Serializable {

    private Long userId;

    private Long categoryId;

    public UserCategoryKey(Long userId, Long categoryId) {
        this.userId = userId;
        this.categoryId = categoryId;
    }
}
