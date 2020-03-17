package com.godev.budgetgo.business.operation;

import com.godev.budgetgo.business.base.DataService;
import com.godev.budgetgo.domain.operation.Operation;
import com.godev.budgetgo.domain.operation.StorageOperationKey;
import com.godev.budgetgo.domain.storage.Storage;

import java.time.LocalDate;
import java.util.List;

public interface OperationsDataService extends DataService<Operation, StorageOperationKey> {

    List<Operation> getByStorage(Storage storage);

    List<Operation> getByStorageAndDateBetween(Storage storage, LocalDate from, LocalDate to);

    void deleteByStorage(Storage storage);
}
