package com.godev.budgetgo.business.storage;

import com.godev.budgetgo.business.base.DataService;
import com.godev.budgetgo.domain.storage.Storage;
import com.godev.budgetgo.domain.storage.StorageRelations;
import com.godev.budgetgo.domain.storage.UserStorageKey;

import java.util.List;
import java.util.Optional;

public interface StoragesRelationsDataService extends DataService<StorageRelations, UserStorageKey> {

    Optional<StorageRelations> findById(UserStorageKey id);

    List<StorageRelations> getByStorage(Storage storage);
}
