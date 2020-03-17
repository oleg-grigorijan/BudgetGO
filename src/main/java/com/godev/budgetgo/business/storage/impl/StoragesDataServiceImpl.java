package com.godev.budgetgo.business.storage.impl;

import com.godev.budgetgo.business.base.BaseDataService;
import com.godev.budgetgo.business.operation.OperationsDataService;
import com.godev.budgetgo.business.operation.OperationsKeySequenceDataService;
import com.godev.budgetgo.business.storage.StoragesDataService;
import com.godev.budgetgo.domain.operation.OperationsKeySequence;
import com.godev.budgetgo.domain.storage.Storage;
import com.godev.budgetgo.domain.user.User;
import com.godev.budgetgo.infra.error.exception.StorageNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class StoragesDataServiceImpl extends BaseDataService<Storage, Long> implements StoragesDataService {

    private final StoragesRepository repository;

    private final OperationsDataService operationsDataService;

    private final OperationsKeySequenceDataService operationsKeySequenceDataService;

    public StoragesDataServiceImpl(
            StoragesRepository repository,
            OperationsDataService operationsDataService,
            OperationsKeySequenceDataService operationsKeySequenceDataService) {
        super(repository, StorageNotFoundException::byId);
        this.repository = repository;
        this.operationsDataService = operationsDataService;
        this.operationsKeySequenceDataService = operationsKeySequenceDataService;
    }

    @Transactional(readOnly = true)
    @Override
    public List<Storage> getByUser(User user) {
        return repository.findByUser(user);
    }

    @Transactional
    @Override
    public Storage add(Storage entity) {
        Storage savedEntity = super.add(entity);
        operationsKeySequenceDataService.createFor(savedEntity);
        return savedEntity;
    }

    @Transactional
    @Override
    public void delete(Storage entity) {
        operationsDataService.deleteByStorage(entity);
        OperationsKeySequence keySequence = operationsKeySequenceDataService.getByStorage(entity);
        operationsKeySequenceDataService.delete(keySequence);
        super.delete(entity);
    }
}
