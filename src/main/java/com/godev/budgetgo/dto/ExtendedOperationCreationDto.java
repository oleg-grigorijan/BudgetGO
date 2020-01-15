package com.godev.budgetgo.dto;

public class ExtendedOperationCreationDto extends OperationCreationDto {

    private Long storageId;

    public ExtendedOperationCreationDto() {
    }

    public ExtendedOperationCreationDto(OperationCreationDto operationCreationDto, Long storageId) {
        this.storageId = storageId;
        setCategoryId(operationCreationDto.getCategoryId());
        setDate(operationCreationDto.getDate());
        setDescription(operationCreationDto.getDescription());
        setMoneyDelta(operationCreationDto.getMoneyDelta());
    }

    public Long getStorageId() {
        return storageId;
    }

    public void setStorageId(Long storageId) {
        this.storageId = storageId;
    }
}
