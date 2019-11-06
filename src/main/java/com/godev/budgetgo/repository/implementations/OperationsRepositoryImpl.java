package com.godev.budgetgo.repository.implementations;

import com.godev.budgetgo.entity.Operation;
import com.godev.budgetgo.entity.Storage;
import com.godev.budgetgo.repository.OperationsRepository;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;
import java.util.List;

@Repository
class OperationsRepositoryImpl
        extends AbstractRepository<Operation, Long>
        implements OperationsRepository {
    OperationsRepositoryImpl() {
        super(Operation.class);
    }

    @Override
    public List<Operation> findByStorage(Storage storage) {
        return entityManager
                .createQuery("SELECT o FROM Operation o WHERE o.storage.id = :storageId", entityClass)
                .setParameter("storageId", storage.getId())
                .getResultList();
    }

    @Override
    public List<Operation> findByStorageAndDateBetween(Storage storage, LocalDate from, LocalDate to) {
        return entityManager
                .createQuery("SELECT o FROM Operation o WHERE o.storage.id = :storageId AND o.date >= :dateFrom AND o.date <= :dateTo", entityClass)
                .setParameter("storageId", storage.getId())
                .setParameter("dateFrom", from)
                .setParameter("dateTo", to)
                .getResultList();
    }

    @Override
    public void deleteByStorage(Storage storage) {
        entityManager
                .createQuery("DELETE FROM Operation o WHERE o.storage.id = :storageId")
                .setParameter("storageId", storage.getId())
                .executeUpdate();
    }
}
