package com.godev.budgetgo.repository;

import com.godev.budgetgo.entity.Operation;
import com.godev.budgetgo.entity.Storage;
import com.godev.budgetgo.entity.StorageOperationKey;

import java.time.LocalDate;
import java.util.List;

public interface OperationsRepository extends Repository<Operation, StorageOperationKey> {
    List<Operation> findByStorage(Storage storage);

    List<Operation> findByStorageAndDateBetween(Storage storage, LocalDate from, LocalDate to);

    void deleteByStorage(Storage storage);
}
