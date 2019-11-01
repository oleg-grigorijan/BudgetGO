package com.godev.budgetgo.service.authorization;

import com.godev.budgetgo.dto.OperationCreationDto;
import com.godev.budgetgo.dto.OperationPatchesDto;
import com.godev.budgetgo.entity.Operation;
import com.godev.budgetgo.entity.Storage;

import java.time.LocalDate;
import java.util.List;

public interface OperationsAuthorizationService
        extends AuthorizationService<Operation, OperationCreationDto, OperationPatchesDto> {

    List<Operation> getAuthorizedEntitiesByStorage(Storage storage);

    List<Operation> getAuthorizedEntitiesByDateBetween(LocalDate from, LocalDate to);

    List<Operation> getAuthorizedEntitiesByStorageAndDateBetween(Storage storage, LocalDate from, LocalDate to);

    void authorizeDelete(Operation entity);
}
