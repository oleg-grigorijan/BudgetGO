package com.godev.budgetgo.service.factory;

import com.godev.budgetgo.dto.OperationInfoDto;
import com.godev.budgetgo.entity.Operation;

public interface OperationDtoFactory extends ConverterFactory<Operation, OperationInfoDto> {
}
