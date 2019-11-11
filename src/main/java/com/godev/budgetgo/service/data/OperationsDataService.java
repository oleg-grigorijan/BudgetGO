package com.godev.budgetgo.service.data;

import com.godev.budgetgo.entity.Operation;
import com.godev.budgetgo.entity.Storage;
import com.godev.budgetgo.entity.StorageOperationKey;

import java.time.LocalDate;
import java.util.List;

public interface OperationsDataService extends DataService<Operation, StorageOperationKey> {
    List<Operation> getByStorage(Storage storage);

    List<Operation> getByStorageAndDateBetween(Storage storage, LocalDate from, LocalDate to);

    /**
     * Removes all operations with given storage in data source.
     * <p>
     * Method doesn't control storage balance validity
     * and should be used only while storage removing.
     *
     * @param storage storage criteria for removing
     */
    void deleteByStorage(Storage storage);
}
