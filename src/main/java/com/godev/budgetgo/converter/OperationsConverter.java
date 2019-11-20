package com.godev.budgetgo.converter;

import com.godev.budgetgo.dto.ExtendedOperationCreationDto;
import com.godev.budgetgo.dto.OperationInfoDto;
import com.godev.budgetgo.dto.OperationPatchesDto;
import com.godev.budgetgo.entity.Operation;

public interface OperationsConverter extends EntityConverter<Operation, OperationInfoDto>, DtoConverter<ExtendedOperationCreationDto, Operation>,
        Merger<Operation, OperationPatchesDto> {
}
