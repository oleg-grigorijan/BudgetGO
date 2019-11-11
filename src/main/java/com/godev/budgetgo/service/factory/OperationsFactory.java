package com.godev.budgetgo.service.factory;

import com.godev.budgetgo.dto.ExtendedOperationCreationDto;
import com.godev.budgetgo.entity.Operation;

public interface OperationsFactory extends ConverterFactory<ExtendedOperationCreationDto, Operation> {
}
