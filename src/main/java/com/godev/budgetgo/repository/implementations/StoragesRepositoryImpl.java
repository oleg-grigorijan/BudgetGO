package com.godev.budgetgo.repository.implementations;

import com.godev.budgetgo.entity.Storage;
import com.godev.budgetgo.repository.StoragesRepository;
import org.springframework.stereotype.Repository;

@Repository
class StoragesRepositoryImpl
        extends AbstractRepository<Storage, Long>
        implements StoragesRepository {

    public StoragesRepositoryImpl() {
        super(Storage.class);
    }
}
