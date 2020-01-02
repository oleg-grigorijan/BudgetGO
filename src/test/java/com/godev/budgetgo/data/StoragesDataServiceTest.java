package com.godev.budgetgo.data;

import com.godev.budgetgo.data.impl.StoragesDataServiceImpl;
import com.godev.budgetgo.entity.OperationsKeySequence;
import com.godev.budgetgo.entity.Storage;
import com.godev.budgetgo.entity.User;
import com.godev.budgetgo.exception.StorageNotFoundException;
import com.godev.budgetgo.repository.StoragesRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.ArrayList;
import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.refEq;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class StoragesDataServiceTest {

    private StoragesDataService dataService;

    @Mock
    private StoragesRepository repository;

    @Mock
    private OperationsDataService operationsDataService;

    @Mock
    private OperationsKeySequenceDataService operationsKeySequenceDataService;

    @BeforeEach
    void setUp() {
        dataService = new StoragesDataServiceImpl(repository, operationsDataService, operationsKeySequenceDataService);
    }

    @Test
    void getById_general_correctReturnValue() {
        Storage entity = new Storage();
        entity.setId(1L);

        when(repository.findById(entity.getId())).thenReturn(Optional.of(entity));

        assertThat(dataService.getById(entity.getId())).isSameAs(entity);
    }

    @Test
    void getById_nonExistingEntity_exceptionThrown() {
        when(repository.findById(any(Long.class))).thenReturn(Optional.empty());

        assertThatThrownBy(() -> dataService.getById(1L)).isInstanceOf(StorageNotFoundException.class);
    }

    @Test
    void getAll_general_correctReturnValue() {
        ArrayList<Storage> entities = new ArrayList<>();
        entities.add(new Storage());
        when(repository.getAll()).thenReturn(entities);

        assertThat(dataService.getAll()).isSameAs(entities);
    }

    @Test
    void getByUser_general_correctReturnValue() {
        User user = new User();
        user.setId(1L);

        ArrayList<Storage> entities = new ArrayList<>();
        entities.add(new Storage());
        when(repository.getByUser(user)).thenReturn(entities);

        assertThat(dataService.getByUser(user)).isSameAs(entities);
    }

    @Test
    void add_general_repositoryAddCall() {
        Storage entity = new Storage();

        dataService.add(entity);
        verify(repository).add(refEq(entity));
    }

    @Test
    void add_general_operationsKeySequenceCreation() {
        Storage entity = new Storage();

        Storage savedEntity = new Storage();
        savedEntity.setId(1L);
        when(repository.add(entity)).thenReturn(savedEntity);

        dataService.add(entity);
        verify(operationsKeySequenceDataService).createFor(refEq(savedEntity));
    }

    @Test
    void update_general_repositoryUpdateCall() {
        Storage entity = new Storage();
        entity.setId(1L);

        dataService.update(entity);
        verify(repository).update(refEq(entity));
    }

    @Test
    void delete_general_repositoryDeleteCall() {
        Storage entity = new Storage();
        entity.setId(1L);

        dataService.delete(entity);
        verify(repository).delete(refEq(entity));
    }

    @Test
    void delete_general_operationsKeySequenceDeletion() {
        Storage entity = new Storage();
        entity.setId(1L);

        OperationsKeySequence keySequence = new OperationsKeySequence();
        keySequence.setStorageId(entity.getId());
        keySequence.setNextOperationId(2L);
        when(operationsKeySequenceDataService.getByStorage(entity)).thenReturn(keySequence);

        dataService.delete(entity);
        verify(operationsKeySequenceDataService).delete(refEq(keySequence));
    }
}
