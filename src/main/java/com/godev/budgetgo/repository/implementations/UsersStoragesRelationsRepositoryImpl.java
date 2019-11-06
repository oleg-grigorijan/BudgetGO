package com.godev.budgetgo.repository.implementations;

import com.godev.budgetgo.entity.Storage;
import com.godev.budgetgo.entity.UserStorageKey;
import com.godev.budgetgo.entity.UserStorageRelations;
import com.godev.budgetgo.repository.UsersStoragesRelationsRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
class UsersStoragesRelationsRepositoryImpl
        extends AbstractRepository<UserStorageRelations, UserStorageKey>
        implements UsersStoragesRelationsRepository {
    UsersStoragesRelationsRepositoryImpl() {
        super(UserStorageRelations.class);
    }

    @Override
    public List<UserStorageRelations> findByStorage(Storage storage) {
        return entityManager
                .createQuery("SELECT r FROM UserStorageRelations r WHERE r.storage.id = :id", entityClass)
                .setParameter("id", storage.getId())
                .getResultList();
    }
}
