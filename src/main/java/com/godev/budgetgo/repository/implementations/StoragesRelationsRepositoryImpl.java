package com.godev.budgetgo.repository.implementations;

import com.godev.budgetgo.entity.Storage;
import com.godev.budgetgo.entity.StorageRelations;
import com.godev.budgetgo.entity.UserStorageKey;
import com.godev.budgetgo.repository.StoragesRelationsRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
class StoragesRelationsRepositoryImpl
        extends AbstractRepository<StorageRelations, UserStorageKey>
        implements StoragesRelationsRepository {
    StoragesRelationsRepositoryImpl() {
        super(StorageRelations.class);
    }

    @Override
    public List<StorageRelations> findByStorage(Storage storage) {
        return entityManager
                .createQuery("SELECT r FROM StorageRelations r WHERE r.storage.id = :id", entityClass)
                .setParameter("id", storage.getId())
                .getResultList();
    }
}
