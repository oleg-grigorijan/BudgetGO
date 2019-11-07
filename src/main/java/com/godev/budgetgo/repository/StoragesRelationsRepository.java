package com.godev.budgetgo.repository;

import com.godev.budgetgo.entity.Storage;
import com.godev.budgetgo.entity.StorageRelations;
import com.godev.budgetgo.entity.UserStorageKey;

import java.util.List;

public interface StoragesRelationsRepository extends Repository<StorageRelations, UserStorageKey> {
    List<StorageRelations> findByStorage(Storage storage);
}
