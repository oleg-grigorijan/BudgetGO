package com.godev.budgetgo.service.data;

import com.godev.budgetgo.entity.Storage;
import com.godev.budgetgo.entity.UserStorageKey;
import com.godev.budgetgo.entity.UserStorageRelations;

import java.util.List;

public interface UsersStoragesRelationsDataService
        extends DataService<UserStorageRelations, UserStorageKey> {

    List<UserStorageRelations> getByStorage(Storage storage);
}
