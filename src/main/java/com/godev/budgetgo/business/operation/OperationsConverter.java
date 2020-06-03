package com.godev.budgetgo.business.operation;

import com.godev.budgetgo.api.rest.operation.dto.ExtendedOperationCreationDto;
import com.godev.budgetgo.api.rest.operation.dto.OperationInfoDto;
import com.godev.budgetgo.api.rest.operation.dto.OperationPatchesDto;
import com.godev.budgetgo.business.base.ConverterToDto;
import com.godev.budgetgo.business.base.ConverterToEntity;
import com.godev.budgetgo.business.base.Merger;
import com.godev.budgetgo.domain.operation.Operation;

public interface OperationsConverter extends ConverterToDto<Operation, OperationInfoDto>, ConverterToEntity<ExtendedOperationCreationDto, Operation>,
        Merger<Operation, OperationPatchesDto> {
}
