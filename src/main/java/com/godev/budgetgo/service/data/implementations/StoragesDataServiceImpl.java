package com.godev.budgetgo.service.data.implementations;

import com.godev.budgetgo.entity.Storage;
import com.godev.budgetgo.exception.StorageNotFoundException;
import com.godev.budgetgo.repository.StoragesRepository;
import com.godev.budgetgo.service.data.OperationsDataService;
import com.godev.budgetgo.service.data.StoragesDataService;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;

@Service
class StoragesDataServiceImpl
        extends AbstractDataService<Storage, Long>
        implements StoragesDataService {

    private final OperationsDataService operationsDataService;

    public StoragesDataServiceImpl(
            StoragesRepository repository,
            OperationsDataService operationsDataService) {
        super(repository, StorageNotFoundException::new);
        this.operationsDataService = operationsDataService;
    }

    @Transactional
    @Override
    public void delete(Storage entity) {
        operationsDataService.deleteByStorage(entity);
        super.delete(entity);
    }
}
