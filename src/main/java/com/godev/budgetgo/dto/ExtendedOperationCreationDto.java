package com.godev.budgetgo.dto;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class ExtendedOperationCreationDto extends OperationCreationDto {

    private Long storageId;

    public ExtendedOperationCreationDto(OperationCreationDto operationCreationDto, Long storageId) {
        this.storageId = storageId;
        setCategoryId(operationCreationDto.getCategoryId());
        setDate(operationCreationDto.getDate());
        setDescription(operationCreationDto.getDescription());
        setMoneyDelta(operationCreationDto.getMoneyDelta());
    }
}
