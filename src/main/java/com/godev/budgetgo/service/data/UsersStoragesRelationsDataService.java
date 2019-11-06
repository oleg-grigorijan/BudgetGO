package com.godev.budgetgo.service.data;

import com.godev.budgetgo.entity.Storage;
import com.godev.budgetgo.entity.UserStorageKey;
import com.godev.budgetgo.entity.UserStorageRelations;

import java.util.List;
import java.util.Optional;

public interface UsersStoragesRelationsDataService
        extends DataService<UserStorageRelations, UserStorageKey> {
    Optional<UserStorageRelations> findById(UserStorageKey id);

    List<UserStorageRelations> getByStorage(Storage storage);
}
