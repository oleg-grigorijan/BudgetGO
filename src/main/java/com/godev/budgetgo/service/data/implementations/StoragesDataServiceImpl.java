package com.godev.budgetgo.service.data.implementations;

import com.godev.budgetgo.entity.Storage;
import com.godev.budgetgo.exception.StorageNotFoundException;
import com.godev.budgetgo.repository.StoragesRepository;
import com.godev.budgetgo.service.data.StoragesDataService;
import org.springframework.stereotype.Service;

@Service
class StoragesDataServiceImpl
        extends AbstractDataService<Storage, Long>
        implements StoragesDataService {

    public StoragesDataServiceImpl(StoragesRepository repository) {
        super(repository, StorageNotFoundException::new);
    }
}
