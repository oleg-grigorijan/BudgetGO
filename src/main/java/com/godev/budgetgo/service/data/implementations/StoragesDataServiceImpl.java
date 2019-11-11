package com.godev.budgetgo.service.data.implementations;

import com.godev.budgetgo.entity.OperationsKeySequence;
import com.godev.budgetgo.entity.Storage;
import com.godev.budgetgo.entity.User;
import com.godev.budgetgo.exception.StorageNotFoundException;
import com.godev.budgetgo.repository.StoragesRepository;
import com.godev.budgetgo.service.data.OperationsDataService;
import com.godev.budgetgo.service.data.OperationsKeySequenceDataService;
import com.godev.budgetgo.service.data.StoragesDataService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
class StoragesDataServiceImpl
        extends AbstractDataService<Storage, Long>
        implements StoragesDataService {

    private final StoragesRepository repository;
    private final OperationsDataService operationsDataService;
    private final OperationsKeySequenceDataService operationsKeySequenceDataService;

    public StoragesDataServiceImpl(
            StoragesRepository repository,
            OperationsDataService operationsDataService,
            OperationsKeySequenceDataService operationsKeySequenceDataService) {
        super(repository, StorageNotFoundException::new);
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
