package com.godev.budgetgo.entity;

import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Embeddable;
import java.io.Serializable;

@Embeddable
@Data
@NoArgsConstructor
public class UserStorageKey implements Serializable {

    private Long userId;

    private Long storageId;

    public UserStorageKey(Long userId, Long storageId) {
        this.userId = userId;
        this.storageId = storageId;
    }
}
