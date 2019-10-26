package com.godev.budgetgo.service.factory;

import com.godev.budgetgo.dto.OperationCreationDto;
import com.godev.budgetgo.entity.Operation;

public interface OperationsFactory extends ConverterFactory<OperationCreationDto, Operation> {
}
