package com.godev.budgetgo.service.data.implementations;

import com.godev.budgetgo.entity.Operation;
import com.godev.budgetgo.entity.Storage;
import com.godev.budgetgo.exception.OperationNotFoundException;
import com.godev.budgetgo.repository.OperationsRepository;
import com.godev.budgetgo.service.data.OperationsDataService;
import com.godev.budgetgo.service.data.StoragesDataService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDate;
import java.util.List;

@Service
class OperationsDataServiceImpl
        extends AbstractDataService<Operation, Long>
        implements OperationsDataService {

    private final OperationsRepository repository;
    private StoragesDataService storagesDataService;

    public OperationsDataServiceImpl(OperationsRepository repository) {
        super(repository, OperationNotFoundException::new);
        this.repository = repository;
    }

    @Autowired
    public void setStoragesDataService(StoragesDataService storagesDataService) {
        this.storagesDataService = storagesDataService;
    }

    @Transactional
    @Override
    public Operation add(Operation entity) {
        Storage storage = entity.getStorage();
        storage.setBalance(storage.getBalance() + entity.getMoneyDelta());
        storagesDataService.update(storage);
        return super.add(entity);
    }

    @Transactional
    @Override
    public Operation update(Operation entity) {
        Operation oldEntity = getById(entity.getId());
        Storage storage = entity.getStorage();

        if (!oldEntity.getStorage().getId().equals(storage.getId())) {
            throw new RuntimeException("Storage of operation can't be modified");
        }

        if (oldEntity.getMoneyDelta() != entity.getMoneyDelta()) {
            storage.setBalance(storage.getBalance()
                    - oldEntity.getMoneyDelta() + entity.getMoneyDelta());
        }

        return super.update(entity);
    }

    @Transactional
    @Override
    public void delete(Operation entity) {
        Storage storage = entity.getStorage();
        storage.setBalance(storage.getBalance() - entity.getMoneyDelta());
        storagesDataService.update(storage);
        super.delete(entity);
    }

    @Transactional(readOnly = true)
    @Override
    public List<Operation> getByStorage(Storage storage) {
        return repository.findByStorage(storage);
    }

    @Transactional(readOnly = true)
    @Override
    public List<Operation> getByStorageAndDateBetween(Storage storage, LocalDate from, LocalDate to) {
        return repository.findByStorageAndDateBetween(storage, from, to);
    }

    @Transactional
    @Override
    public void deleteByStorage(Storage storage) {
        repository.deleteByStorage(storage);
    }
}
