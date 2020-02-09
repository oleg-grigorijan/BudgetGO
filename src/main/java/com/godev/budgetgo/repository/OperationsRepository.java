package com.godev.budgetgo.repository;

import com.godev.budgetgo.entity.Operation;
import com.godev.budgetgo.entity.Storage;
import com.godev.budgetgo.entity.StorageOperationKey;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.time.LocalDate;
import java.util.List;

public interface OperationsRepository extends JpaRepository<Operation, StorageOperationKey> {

    List<Operation> findByStorage(Storage storage);

    List<Operation> findByStorageAndDateBetween(Storage storage, LocalDate from, LocalDate to);

    @Query("DELETE FROM Operation o WHERE o.storage.id = :#{#storage.id}")
    @Modifying
    void deleteByStorage(@Param("storage") Storage storage);
}
