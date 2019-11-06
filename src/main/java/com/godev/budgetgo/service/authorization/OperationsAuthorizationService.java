package com.godev.budgetgo.service.authorization;

import com.godev.budgetgo.entity.Operation;
import com.godev.budgetgo.entity.Storage;

import java.time.LocalDate;
import java.util.List;

public interface OperationsAuthorizationService
        extends AuthorizationService<Operation> {

    List<Operation> getAuthorizedEntitiesByStorage(Storage storage);

    List<Operation> getAuthorizedEntitiesByStorageAndDateBetween(Storage storage, LocalDate from, LocalDate to);

    void authorizeDelete(Operation entity);
}
