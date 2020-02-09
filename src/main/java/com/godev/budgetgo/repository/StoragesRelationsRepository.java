package com.godev.budgetgo.repository;

import com.godev.budgetgo.entity.Storage;
import com.godev.budgetgo.entity.StorageRelations;
import com.godev.budgetgo.entity.UserStorageKey;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface StoragesRelationsRepository extends JpaRepository<StorageRelations, UserStorageKey> {

    List<StorageRelations> findByStorage(Storage storage);
}
