package com.godev.budgetgo.data;

import com.godev.budgetgo.entity.Operation;
import com.godev.budgetgo.entity.Storage;
import com.godev.budgetgo.entity.StorageOperationKey;

import java.time.LocalDate;
import java.util.List;

public interface OperationsDataService extends DataService<Operation, StorageOperationKey> {
    List<Operation> getByStorage(Storage storage);

    List<Operation> getByStorageAndDateBetween(Storage storage, LocalDate from, LocalDate to);

    void deleteByStorage(Storage storage);
}
