package com.godev.budgetgo.business.operation.impl;

import com.godev.budgetgo.business.operation.OperationsKeySequenceDataService;
import com.godev.budgetgo.domain.operation.OperationsKeySequence;
import com.godev.budgetgo.domain.storage.Storage;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class OperationsKeySequenceDataServiceImpl implements OperationsKeySequenceDataService {

    private final OperationsKeySequenceRepository repository;

    public OperationsKeySequenceDataServiceImpl(OperationsKeySequenceRepository repository) {
        this.repository = repository;
    }

    @Transactional(readOnly = true)
    @Override
    public OperationsKeySequence getByStorage(Storage storage) {
        return repository
                .findById(storage.getId())
                .orElseThrow(() -> new RuntimeException("OperationsKeySequence doesn't exist"));
    }

    @Transactional
    @Override
    public OperationsKeySequence createFor(Storage storage) {
        OperationsKeySequence entity = new OperationsKeySequence();
        entity.setStorageId(storage.getId());
        entity.setNextOperationId(1L);
        return repository.save(entity);
    }

    @Transactional
    @Override
    public OperationsKeySequence increment(OperationsKeySequence entity) {
        entity.setNextOperationId(entity.getNextOperationId() + 1);
        return repository.save(entity);
    }

    @Transactional
    @Override
    public void delete(OperationsKeySequence entity) {
        repository.delete(entity);
    }
}
