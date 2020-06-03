package com.godev.budgetgo.business.storage.impl;

import com.godev.budgetgo.domain.storage.Storage;
import com.godev.budgetgo.domain.storage.StorageRelations;
import com.godev.budgetgo.domain.storage.UserStorageKey;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface StoragesRelationsRepository extends JpaRepository<StorageRelations, UserStorageKey> {

    List<StorageRelations> findByStorage(Storage storage);
}
