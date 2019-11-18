package com.godev.budgetgo.service.data;

import com.godev.budgetgo.entity.Storage;
import com.godev.budgetgo.entity.StorageRelations;
import com.godev.budgetgo.entity.UserStorageKey;

import java.util.List;
import java.util.Optional;

public interface StoragesRelationsDataService extends DataService<StorageRelations, UserStorageKey> {
    Optional<StorageRelations> findById(UserStorageKey id);

    List<StorageRelations> getByStorage(Storage storage);
}
