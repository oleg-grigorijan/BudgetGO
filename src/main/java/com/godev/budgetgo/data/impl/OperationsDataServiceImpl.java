package com.godev.budgetgo.data.impl;

import com.godev.budgetgo.data.OperationsDataService;
import com.godev.budgetgo.data.OperationsKeySequenceDataService;
import com.godev.budgetgo.data.StoragesDataService;
import com.godev.budgetgo.entity.Operation;
import com.godev.budgetgo.entity.OperationsKeySequence;
import com.godev.budgetgo.entity.Storage;
import com.godev.budgetgo.entity.StorageOperationKey;
import com.godev.budgetgo.exception.BalanceOverflowException;
import com.godev.budgetgo.exception.OperationNotFoundException;
import com.godev.budgetgo.repository.OperationsRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Lazy;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDate;
import java.util.List;

@Service
public class OperationsDataServiceImpl extends AbstractDataService<Operation, StorageOperationKey> implements OperationsDataService {

    private final OperationsRepository repository;
    private StoragesDataService storagesDataService;
    private final OperationsKeySequenceDataService keySequenceDataService;

    public OperationsDataServiceImpl(OperationsRepository repository, OperationsKeySequenceDataService keySequenceDataService) {
        super(repository, OperationNotFoundException::byId);
        this.repository = repository;
        this.keySequenceDataService = keySequenceDataService;
    }

    @Autowired
    @Lazy
    public void setStoragesDataService(StoragesDataService storagesDataService) {
        this.storagesDataService = storagesDataService;
    }

    @Transactional
    @Override
    public Operation add(Operation entity) {
        Storage storage = entity.getStorage();

        entity.setId(getNextIdFor(storage));

        try {
            storage.setBalance(Math.addExact(storage.getBalance(), entity.getMoneyDelta()));
        } catch (ArithmeticException ex) {
            throw BalanceOverflowException.ofStorage(storage);
        }

        return super.add(entity);
    }

    @Transactional
    @Override
    public Operation update(Operation entity) {
        Operation oldEntity = getById(entity.getId());
        Storage storage = entity.getStorage();

        if (oldEntity.getMoneyDelta() != entity.getMoneyDelta()) {
            try {
                storage.setBalance(Math.addExact(Math.subtractExact(storage.getBalance(), oldEntity.getMoneyDelta()), entity.getMoneyDelta()));
            } catch (ArithmeticException ex) {
                throw BalanceOverflowException.ofStorage(storage);
            }
        }

        return super.update(entity);
    }

    @Transactional
    @Override
    public void delete(Operation entity) {
        Storage storage = entity.getStorage();

        try {
            storage.setBalance(Math.subtractExact(storage.getBalance(), entity.getMoneyDelta()));
        } catch (ArithmeticException ex) {
            throw BalanceOverflowException.ofStorage(storage);
        }

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
        storage.setBalance(storage.getInitialBalance());
        storagesDataService.update(storage);
    }

    private StorageOperationKey getNextIdFor(Storage storage) {
        OperationsKeySequence keySequence = keySequenceDataService.getByStorage(storage);
        StorageOperationKey result = new StorageOperationKey(keySequence.getStorageId(), keySequence.getNextOperationId());
        keySequenceDataService.increment(keySequence);
        return result;
    }
}
