package com.godev.budgetgo.api.rest.operation.dto;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

@Data
@EqualsAndHashCode(callSuper = true)
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
