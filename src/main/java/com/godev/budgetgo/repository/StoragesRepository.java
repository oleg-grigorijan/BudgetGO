package com.godev.budgetgo.repository;

import com.godev.budgetgo.entity.Storage;
import com.godev.budgetgo.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface StoragesRepository extends JpaRepository<Storage, Long> {

    @Query("SELECT s FROM Storage s WHERE s.id IN (SELECT r.storage.id FROM StorageRelations r WHERE r.user.id = :#{#user.id})")
    List<Storage> findByUser(@Param("user") User user);
}
