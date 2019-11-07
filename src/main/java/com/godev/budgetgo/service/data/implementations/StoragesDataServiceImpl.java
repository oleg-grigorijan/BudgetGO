package com.godev.budgetgo.service.data.implementations;

import com.godev.budgetgo.entity.Storage;
import com.godev.budgetgo.entity.User;
import com.godev.budgetgo.exception.StorageNotFoundException;
import com.godev.budgetgo.repository.StoragesRepository;
import com.godev.budgetgo.service.data.OperationsDataService;
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

    public StoragesDataServiceImpl(
            StoragesRepository repository,
            OperationsDataService operationsDataService) {
        super(repository, StorageNotFoundException::new);
        this.repository = repository;
        this.operationsDataService = operationsDataService;
    }

    @Transactional(readOnly = true)
    @Override
    public List<Storage> getByUser(User user) {
        return repository.findByUser(user);
    }

    @Transactional
    @Override
    public void delete(Storage entity) {
        operationsDataService.deleteByStorage(entity);
        super.delete(entity);
    }
}
