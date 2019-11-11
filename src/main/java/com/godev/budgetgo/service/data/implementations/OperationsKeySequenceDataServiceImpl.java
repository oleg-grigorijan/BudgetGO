package com.godev.budgetgo.service.data.implementations;

import com.godev.budgetgo.entity.OperationsKeySequence;
import com.godev.budgetgo.entity.Storage;
import com.godev.budgetgo.repository.OperationsKeySequenceRepository;
import com.godev.budgetgo.service.data.OperationsKeySequenceDataService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
class OperationsKeySequenceDataServiceImpl implements OperationsKeySequenceDataService {

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
        return repository.add(entity);
    }

    @Transactional
    @Override
    public OperationsKeySequence increment(OperationsKeySequence entity) {
        entity.setNextOperationId(entity.getNextOperationId() + 1);
        return repository.update(entity);
    }

    @Transactional
    @Override
    public void delete(OperationsKeySequence entity) {
        repository.delete(entity);
    }
}
