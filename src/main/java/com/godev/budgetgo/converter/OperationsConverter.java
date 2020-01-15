package com.godev.budgetgo.converter;

import com.godev.budgetgo.dto.ExtendedOperationCreationDto;
import com.godev.budgetgo.dto.OperationInfoDto;
import com.godev.budgetgo.dto.OperationPatchesDto;
import com.godev.budgetgo.entity.Operation;

public interface OperationsConverter extends ConverterToDto<Operation, OperationInfoDto>, ConverterToEntity<ExtendedOperationCreationDto, Operation>,
        Merger<Operation, OperationPatchesDto> {
}
