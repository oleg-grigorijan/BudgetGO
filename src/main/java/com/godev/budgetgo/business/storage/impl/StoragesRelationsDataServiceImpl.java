package com.godev.budgetgo.business.storage.impl;

import com.godev.budgetgo.business.base.BaseDataService;
import com.godev.budgetgo.business.storage.StoragesDataService;
import com.godev.budgetgo.business.storage.StoragesRelationsDataService;
import com.godev.budgetgo.domain.storage.Storage;
import com.godev.budgetgo.domain.storage.StorageRelations;
import com.godev.budgetgo.domain.storage.UserStorageKey;
import com.godev.budgetgo.infra.error.exception.StorageRelationsNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Service
public class StoragesRelationsDataServiceImpl extends BaseDataService<StorageRelations, UserStorageKey> implements StoragesRelationsDataService {

    private final StoragesRelationsRepository repository;

    private final StoragesDataService storagesDataService;

    public StoragesRelationsDataServiceImpl(StoragesRelationsRepository repository, StoragesDataService storagesDataService) {
        super(repository, StorageRelationsNotFoundException::byId);
        this.repository = repository;
        this.storagesDataService = storagesDataService;
    }

    @Transactional(readOnly = true)
    @Override
    public Optional<StorageRelations> findById(UserStorageKey id) {
        return repository.findById(id);
    }

    @Transactional(readOnly = true)
    @Override
    public List<StorageRelations> getByStorage(Storage storage) {
        return repository.findByStorage(storage);
    }

    @Transactional
    @Override
    public void delete(StorageRelations entity) {
        super.delete(entity);
        Storage storage = entity.getStorage();
        if (getByStorage(storage).isEmpty()) {
            storagesDataService.delete(storage);
        }
    }
}
