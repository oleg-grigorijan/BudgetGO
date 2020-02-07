package com.godev.budgetgo.dto;

import com.godev.budgetgo.entity.UserStorageRole;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

import java.util.Optional;

@Setter
@EqualsAndHashCode
@ToString
@NoArgsConstructor
public class StorageRelationsPatchesDto {

    private UserStorageRole userRole;

    public Optional<UserStorageRole> getUserRole() {
        return Optional.ofNullable(userRole);
    }
}
