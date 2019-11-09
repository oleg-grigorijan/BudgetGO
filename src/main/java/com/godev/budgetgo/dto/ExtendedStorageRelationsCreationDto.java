package com.godev.budgetgo.dto;

public class ExtendedStorageRelationsCreationDto extends StorageRelationsCreationDto {

    private Long storageId;

    public ExtendedStorageRelationsCreationDto() {
    }

    public ExtendedStorageRelationsCreationDto(
            StorageRelationsCreationDto relationsCreationDto,
            Long storageId
    ) {
        this.storageId = storageId;
        setUserId(relationsCreationDto.getUserId());
        setUserRole(relationsCreationDto.getUserRole());
    }

    public Long getStorageId() {
        return storageId;
    }

    public void setStorageId(Long storageId) {
        this.storageId = storageId;
    }
}
