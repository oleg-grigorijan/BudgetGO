package com.godev.budgetgo.api.rest.storage.dto;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

@Data
@EqualsAndHashCode(callSuper = true)
@NoArgsConstructor
public class ExtendedStorageRelationsCreationDto extends StorageRelationsCreationDto {

    private Long storageId;

    public ExtendedStorageRelationsCreationDto(StorageRelationsCreationDto relationsCreationDto, Long storageId) {
        this.storageId = storageId;
        setUserId(relationsCreationDto.getUserId());
        setUserRole(relationsCreationDto.getUserRole());
    }
}
