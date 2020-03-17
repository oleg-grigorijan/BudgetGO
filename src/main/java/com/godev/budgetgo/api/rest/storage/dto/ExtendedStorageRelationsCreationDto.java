package com.godev.budgetgo.api.rest.storage.dto;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class ExtendedStorageRelationsCreationDto extends StorageRelationsCreationDto {

    private Long storageId;

    public ExtendedStorageRelationsCreationDto(StorageRelationsCreationDto relationsCreationDto, Long storageId) {
        this.storageId = storageId;
        setUserId(relationsCreationDto.getUserId());
        setUserRole(relationsCreationDto.getUserRole());
    }
}
