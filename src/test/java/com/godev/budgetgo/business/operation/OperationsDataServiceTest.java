package com.godev.budgetgo.business.operation;

import com.godev.budgetgo.UnitTest;
import com.godev.budgetgo.business.operation.impl.OperationsDataServiceImpl;
import com.godev.budgetgo.business.operation.impl.OperationsRepository;
import com.godev.budgetgo.business.storage.StoragesDataService;
import com.godev.budgetgo.domain.operation.Operation;
import com.godev.budgetgo.domain.operation.OperationsKeySequence;
import com.godev.budgetgo.domain.operation.StorageOperationKey;
import com.godev.budgetgo.domain.storage.Storage;
import com.godev.budgetgo.infra.error.exception.BalanceOverflowException;
import com.godev.budgetgo.infra.error.exception.OperationNotFoundException;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Optional;
import java.util.stream.Stream;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;
import static org.junit.jupiter.params.provider.Arguments.arguments;
import static org.mockito.AdditionalAnswers.returnsFirstArg;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.refEq;
import static org.mockito.Mockito.never;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
@UnitTest
class OperationsDataServiceTest {

    private OperationsDataService dataService;

    @Mock
    private OperationsRepository repository;

    @Mock
    private OperationsKeySequenceDataService keySequenceDataService;

    @Mock
    private StoragesDataService storagesDataService;

    @BeforeEach
    void setUp() {
        OperationsDataServiceImpl dataService = new OperationsDataServiceImpl(repository, keySequenceDataService);
        dataService.setStoragesDataService(storagesDataService);
        this.dataService = dataService;
    }

    @Test
    void getById_general_correctReturnValue() {
        Operation expectedEntity = new Operation();
        expectedEntity.setStorage(new Storage());
        expectedEntity.getStorage().setId(1L);
        StorageOperationKey id = new StorageOperationKey(expectedEntity.getStorage().getId(), 2L);
        expectedEntity.setId(id);

        when(repository.findById(id)).thenReturn(Optional.of(expectedEntity));

        assertThat(dataService.getById(id)).isSameAs(expectedEntity);
    }

    @Test
    void getById_nonExistingEntity_exceptionThrown() {
        when(repository.findById(any(StorageOperationKey.class))).thenReturn(Optional.empty());

        assertThatThrownBy(() -> dataService.getById(new StorageOperationKey(1L, 2L)))
                .isInstanceOf(OperationNotFoundException.class);
    }

    @Test
    void getAll_general_correctReturnValue() {
        ArrayList<Operation> entities = new ArrayList<>();
        entities.add(new Operation());
        when(repository.findAll()).thenReturn(entities);

        assertThat(dataService.getAll()).isSameAs(entities);
    }

    @Test
    void getByStorage_general_correctReturnValue() {
        Storage storage = new Storage();
        storage.setId(1L);

        ArrayList<Operation> entities = new ArrayList<>();
        entities.add(new Operation());
        when(repository.findByStorage(storage)).thenReturn(entities);

        assertThat(dataService.getByStorage(storage)).isSameAs(entities);
    }

    @Test
    void getByStorageAndDateBetween_general_correctReturnValue() {
        Storage storage = new Storage();
        storage.setId(1L);
        LocalDate dateFrom = LocalDate.MIN;
        LocalDate dateTo = LocalDate.MAX;

        ArrayList<Operation> entities = new ArrayList<>();
        entities.add(new Operation());
        when(repository.findByStorageAndDateBetween(storage, dateFrom, dateTo)).thenReturn(entities);

        assertThat(dataService.getByStorageAndDateBetween(storage, dateFrom, dateTo)).isSameAs(entities);
    }

    @Test
    void add_general_repositoryAddCall() {
        Operation entity = new Operation();
        entity.setStorage(new Storage());
        entity.getStorage().setId(1L);

        when(keySequenceDataService.getByStorage(entity.getStorage()))
                .thenReturn(new OperationsKeySequence(entity.getStorage().getId(), 2L));

        dataService.add(entity);
        verify(repository).save(refEq(entity));
    }

    @Test
    void add_general_idSpecification() {
        Operation entity = new Operation();
        entity.setStorage(new Storage());
        entity.getStorage().setId(1L);

        OperationsKeySequence keySequence = new OperationsKeySequence(entity.getStorage().getId(), 2L);
        when(keySequenceDataService.getByStorage(entity.getStorage()))
                .thenReturn(keySequence);
        when(repository.save(any(Operation.class))).then(returnsFirstArg());

        assertThat(dataService.add(entity).getId())
                .isEqualToComparingFieldByField(new StorageOperationKey(keySequence.getStorageId(), keySequence.getNextOperationId()));
    }

    @Test
    void add_general_keySequenceIncrement() {
        Operation entity = new Operation();
        entity.setStorage(new Storage());
        entity.getStorage().setId(1L);

        OperationsKeySequence keySequence = new OperationsKeySequence(entity.getStorage().getId(), 2L);
        when(keySequenceDataService.getByStorage(entity.getStorage()))
                .thenReturn(keySequence);

        dataService.add(entity);
        verify(keySequenceDataService).increment(refEq(keySequence));
    }

    private static Stream<Arguments> params_add_general_storageBalanceChange() {
        return Stream.of(
                arguments(500L, 200L),
                arguments(100L, -400L),
                arguments(0L, 0L)
        );
    }

    @ParameterizedTest
    @MethodSource("params_add_general_storageBalanceChange")
    void add_general_storageBalanceChange(long startingBalance, long moneyDelta) {
        Operation entity = new Operation();
        entity.setMoneyDelta(moneyDelta);
        entity.setStorage(new Storage());
        entity.getStorage().setId(1L);
        entity.getStorage().setBalance(startingBalance);

        when(keySequenceDataService.getByStorage(entity.getStorage()))
                .thenReturn(new OperationsKeySequence(entity.getStorage().getId(), 2L));
        when(repository.save(any(Operation.class))).then(returnsFirstArg());

        assertThat(dataService.add(entity).getStorage().getBalance()).isEqualTo(startingBalance + moneyDelta);
    }

    private static Stream<Arguments> params_add_causeBalanceOverflow_exceptionThrownAndNoRepositoryAddCall() {
        return Stream.of(
                arguments(Long.MAX_VALUE, 1L),
                arguments(Long.MIN_VALUE, -1L)
        );
    }

    @ParameterizedTest
    @MethodSource("params_add_causeBalanceOverflow_exceptionThrownAndNoRepositoryAddCall")
    void add_causeBalanceOverflow_exceptionThrownAndNoRepositoryAddCall(long startingBalance, long moneyDelta) {
        Operation entity = new Operation();
        entity.setMoneyDelta(moneyDelta);
        entity.setStorage(new Storage());
        entity.getStorage().setId(1L);
        entity.getStorage().setBalance(startingBalance);

        when(keySequenceDataService.getByStorage(entity.getStorage()))
                .thenReturn(new OperationsKeySequence(entity.getStorage().getId(), 2L));

        assertThatThrownBy(() -> dataService.add(entity)).isInstanceOf(BalanceOverflowException.class);
        verify(repository, never()).save(any(Operation.class));
    }

    @Test
    void update_general_repositoryUpdateCall() {
        Storage storage = new Storage();
        storage.setId(1L);

        Operation oldEntity = new Operation();
        oldEntity.setStorage(storage);
        oldEntity.setId(new StorageOperationKey(storage.getId(), 2L));

        Operation entity = new Operation();
        entity.setStorage(storage);
        entity.setId(oldEntity.getId());

        when(repository.findById(entity.getId())).thenReturn(Optional.of(oldEntity));

        dataService.update(entity);
        verify(repository).save(refEq(entity));
    }

    private static Stream<Arguments> params_update_general_storageBalanceChange() {
        return Stream.of(
                arguments(500L, 200L, 100L),
                arguments(-100L, 200L, 300L),
                arguments(100L, 200L, -200L),
                arguments(0L, 0L, 0L)
        );
    }

    @ParameterizedTest
    @MethodSource("params_update_general_storageBalanceChange")
    void update_general_storageBalanceChange(long startingBalance, long oldMoneyDelta, long newMoneyDelta) {
        Storage storage = new Storage();
        storage.setId(1L);
        storage.setBalance(startingBalance);

        Operation oldEntity = new Operation();
        oldEntity.setStorage(storage);
        oldEntity.setId(new StorageOperationKey(storage.getId(), 2L));
        oldEntity.setMoneyDelta(oldMoneyDelta);

        Operation entity = new Operation();
        entity.setStorage(storage);
        entity.setId(oldEntity.getId());
        entity.setMoneyDelta(newMoneyDelta);

        when(repository.findById(entity.getId())).thenReturn(Optional.of(oldEntity));
        when(repository.save(any(Operation.class))).then(returnsFirstArg());

        assertThat(dataService.update(entity).getStorage().getBalance())
                .isEqualTo(startingBalance - oldMoneyDelta + newMoneyDelta);
    }

    private static Stream<Arguments> params_update_causeBalanceOverflow_exceptionThrownAndNoRepositoryUpdateCall() {
        return Stream.of(
                arguments(Long.MAX_VALUE - 100L, 200L, 301L),
                arguments(0L, -100L, Long.MAX_VALUE),
                arguments(Long.MIN_VALUE, -100L, -200L),
                arguments(-100L, 0L, Long.MIN_VALUE)
        );
    }

    @ParameterizedTest
    @MethodSource("params_update_causeBalanceOverflow_exceptionThrownAndNoRepositoryUpdateCall")
    void update_causeBalanceOverflow_exceptionThrownAndNoRepositoryUpdateCall(long startingBalance, long oldMoneyDelta, long newMoneyDelta) {
        Storage storage = new Storage();
        storage.setId(1L);
        storage.setBalance(startingBalance);

        Operation oldEntity = new Operation();
        oldEntity.setStorage(storage);
        oldEntity.setId(new StorageOperationKey(storage.getId(), 2L));
        oldEntity.setMoneyDelta(oldMoneyDelta);

        Operation entity = new Operation();
        entity.setStorage(storage);
        entity.setId(oldEntity.getId());
        entity.setMoneyDelta(newMoneyDelta);

        when(repository.findById(entity.getId())).thenReturn(Optional.of(oldEntity));

        assertThatThrownBy(() -> dataService.update(entity)).isInstanceOf(BalanceOverflowException.class);
        verify(repository, never()).save(any(Operation.class));
    }

    @Test
    void delete_general_repositoryDeleteCall() {
        Operation entity = new Operation();
        entity.setStorage(new Storage());
        entity.getStorage().setId(1L);
        entity.setId(new StorageOperationKey(entity.getStorage().getId(), 2L));

        dataService.delete(entity);
        verify(repository).delete(refEq(entity));
    }

    private static Stream<Arguments> params_delete_general_storageBalanceChange() {
        return Stream.of(
                arguments(500L, 200L),
                arguments(100L, -400L),
                arguments(0L, 0L)
        );
    }

    @ParameterizedTest
    @MethodSource("params_delete_general_storageBalanceChange")
    void delete_general_storageBalanceChange(Long startingBalance, Long moneyDelta) {
        Storage storage = new Storage();
        storage.setId(1L);
        storage.setBalance(startingBalance);
        Operation entity = new Operation();
        entity.setId(new StorageOperationKey(storage.getId(), 2L));
        entity.setStorage(storage);
        entity.setMoneyDelta(moneyDelta);

        dataService.delete(entity);
        assertThat(storage.getBalance()).isEqualTo(startingBalance - moneyDelta);
    }

    private static Stream<Arguments> params_delete_causeBalanceOverflow_exceptionThrownAndNoRepositoryDeleteCall() {
        return Stream.of(
                arguments(Long.MAX_VALUE, -1L),
                arguments(Long.MIN_VALUE, 1L)
        );
    }

    @ParameterizedTest
    @MethodSource("params_delete_causeBalanceOverflow_exceptionThrownAndNoRepositoryDeleteCall")
    void delete_causeBalanceOverflow_exceptionThrownAndNoRepositoryDeleteCall(Long startingBalance, Long moneyDelta) {
        Storage storage = new Storage();
        storage.setId(1L);
        storage.setBalance(startingBalance);
        Operation entity = new Operation();
        entity.setId(new StorageOperationKey(storage.getId(), 2L));
        entity.setStorage(storage);
        entity.setMoneyDelta(moneyDelta);

        assertThatThrownBy(() -> dataService.delete(entity)).isInstanceOf(BalanceOverflowException.class);
        verify(repository, never()).delete(any(Operation.class));
    }

    @Test
    void deleteByStorage_general_repositoryDeleteByStorageCall() {
        Storage storage = new Storage();
        storage.setId(1L);

        dataService.deleteByStorage(storage);
        verify(repository).deleteByStorage(refEq(storage));
    }

    @Test
    void deleteByStorage_general_storageBalanceChange() {
        Storage storage = new Storage();
        storage.setId(1L);
        storage.setBalance(100L);
        storage.setInitialBalance(200L);

        Storage expectedStorage = new Storage();
        expectedStorage.setId(storage.getId());
        expectedStorage.setBalance(storage.getInitialBalance());
        expectedStorage.setInitialBalance(storage.getInitialBalance());

        dataService.deleteByStorage(storage);
        verify(storagesDataService).update(refEq(expectedStorage));
    }
}
