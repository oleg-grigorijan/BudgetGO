package com.godev.budgetgo.business.operation;

import com.godev.budgetgo.UnitTest;
import com.godev.budgetgo.business.operation.impl.OperationsKeySequenceDataServiceImpl;
import com.godev.budgetgo.business.operation.impl.OperationsKeySequenceRepository;
import com.godev.budgetgo.domain.operation.OperationsKeySequence;
import com.godev.budgetgo.domain.storage.Storage;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;
import static org.mockito.AdditionalAnswers.returnsFirstArg;
import static org.mockito.Mockito.any;
import static org.mockito.Mockito.refEq;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
@UnitTest
class OperationsKeySequenceDataServiceTest {

    private OperationsKeySequenceDataService dataService;

    @Mock
    private OperationsKeySequenceRepository repository;

    @BeforeEach
    void setUp() {
        dataService = new OperationsKeySequenceDataServiceImpl(repository);
    }

    @Test
    void getByStorage_general_correctReturnValue() {
        Storage storage = new Storage();
        storage.setId(2L);

        OperationsKeySequence entity = new OperationsKeySequence();
        entity.setStorageId(storage.getId());
        entity.setNextOperationId(1L);

        when(repository.findById(entity.getStorageId())).thenReturn(Optional.of(entity));

        assertThat(dataService.getByStorage(storage)).isSameAs(entity);
    }

    @Test
    void getByStorage_nonExistingEntity_exceptionThrown() {
        Storage storage = new Storage();
        storage.setId(1L);
        when(repository.findById(storage.getId())).thenReturn(Optional.empty());

        assertThatThrownBy(() -> dataService.getByStorage(storage)).isInstanceOf(RuntimeException.class);
    }

    @Test
    void createFor_general_correctReturnValueAndRepositoryAddCall() {
        Storage storage = new Storage();
        storage.setId(2L);

        OperationsKeySequence expectedEntity = new OperationsKeySequence();
        expectedEntity.setStorageId(storage.getId());
        expectedEntity.setNextOperationId(1L);

        when(repository.save(any(OperationsKeySequence.class))).then(returnsFirstArg());

        assertThat(dataService.createFor(storage)).isEqualToComparingFieldByField(expectedEntity);
        verify(repository).save(refEq(expectedEntity));
    }

    @Test
    void increment_general_correctReturnValueAndRepositoryUpdateCall() {
        OperationsKeySequence entity = new OperationsKeySequence();
        entity.setStorageId(1L);
        entity.setNextOperationId(2L);

        OperationsKeySequence expectedEntity = new OperationsKeySequence();
        expectedEntity.setStorageId(entity.getStorageId());
        expectedEntity.setNextOperationId(entity.getNextOperationId() + 1);

        when(repository.save(any(OperationsKeySequence.class))).then(returnsFirstArg());

        assertThat(dataService.increment(entity)).isEqualToComparingFieldByField(expectedEntity);
        verify(repository).save(refEq(expectedEntity));
    }

    @Test
    void delete_general_repositoryDeleteCall() {
        OperationsKeySequence entity = new OperationsKeySequence();
        entity.setStorageId(1L);
        entity.setNextOperationId(2L);

        dataService.delete(entity);
        verify(repository).delete(refEq(entity));
    }
}
