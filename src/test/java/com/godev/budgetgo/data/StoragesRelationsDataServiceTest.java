package com.godev.budgetgo.data;

import com.godev.budgetgo.data.impl.StoragesRelationsDataServiceImpl;
import com.godev.budgetgo.entity.Storage;
import com.godev.budgetgo.entity.StorageRelations;
import com.godev.budgetgo.entity.UserStorageKey;
import com.godev.budgetgo.exception.StorageRelationsNotFoundException;
import com.godev.budgetgo.repository.StoragesRelationsRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;
import static org.mockito.Mockito.any;
import static org.mockito.Mockito.never;
import static org.mockito.Mockito.refEq;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class StoragesRelationsDataServiceTest {

    private StoragesRelationsDataService dataService;

    @Mock
    private StoragesRelationsRepository repository;

    @Mock
    private StoragesDataService storagesDataService;

    @BeforeEach
    void setUp() {
        dataService = new StoragesRelationsDataServiceImpl(repository, storagesDataService);
    }

    @Test
    void getById_general_correctReturnValue() {
        StorageRelations entity = new StorageRelations();
        entity.setId(new UserStorageKey(1L, 2L));

        when(repository.findById(entity.getId())).thenReturn(Optional.of(entity));

        assertThat(dataService.getById(entity.getId())).isSameAs(entity);
    }

    @Test
    void getById_nonExistingEntity_exceptionThrown() {
        when(repository.findById(any(UserStorageKey.class))).thenReturn(Optional.empty());

        assertThatThrownBy(() -> dataService.getById(new UserStorageKey(1L, 2L)))
                .isInstanceOf(StorageRelationsNotFoundException.class);
    }

    @Test
    void getAll_general_correctReturnValue() {
        ArrayList<StorageRelations> entities = new ArrayList<>();
        entities.add(new StorageRelations());
        when(repository.getAll()).thenReturn(entities);

        assertThat(dataService.getAll()).isSameAs(entities);
    }

    @Test
    void findById_general_correctReturnValue() {
        StorageRelations entity = new StorageRelations();
        entity.setId(new UserStorageKey(1L, 2L));

        when(repository.findById(entity.getId())).thenReturn(Optional.of(entity));

        assertThat(dataService.findById(entity.getId())).get().isEqualToComparingFieldByField(entity);
    }

    @Test
    void findById_emptyOptional_correctReturnValue() {
        when(repository.findById(any(UserStorageKey.class))).thenReturn(Optional.empty());

        assertThat(dataService.findById(new UserStorageKey(1L, 2L))).isEmpty();
    }

    @Test
    void getByStorage() {
        Storage storage = new Storage();
        storage.setId(1L);

        ArrayList<StorageRelations> entities = new ArrayList<>();
        entities.add(new StorageRelations());
        when(repository.getByStorage(storage)).thenReturn(entities);

        assertThat(dataService.getByStorage(storage)).isSameAs(entities);
    }

    @Test
    void add_general_repositoryAddCall() {
        StorageRelations entity = new StorageRelations();

        dataService.add(entity);
        verify(repository).add(refEq(entity));
    }

    @Test
    void update_general_repositoryUpdateCall() {
        StorageRelations entity = new StorageRelations();
        entity.setId(new UserStorageKey(1L, 2L));

        dataService.update(entity);
        verify(repository).update(refEq(entity));
    }

    @Test
    void delete_general_repositoryDeleteCall() {
        StorageRelations entity = new StorageRelations();
        entity.setId(new UserStorageKey(1L, 2L));

        dataService.delete(entity);
        verify(repository).delete(refEq(entity));
    }

    @Test
    void delete_noMoreStorageRelations_storageDataServiceDeleteCall() {
        StorageRelations entity = new StorageRelations();
        entity.setStorage(new Storage());
        entity.getStorage().setId(2L);
        entity.setId(new UserStorageKey(1L, entity.getStorage().getId()));

        when(repository.getByStorage(entity.getStorage())).thenReturn(Collections.emptyList());

        dataService.delete(entity);
        verify(storagesDataService).delete(refEq(entity.getStorage()));
    }

    @Test
    void delete_hasMoreStorageRelations_NoStorageDataServiceDeleteCall() {
        StorageRelations entity = new StorageRelations();
        entity.setStorage(new Storage());
        entity.getStorage().setId(2L);
        entity.setId(new UserStorageKey(1L, entity.getStorage().getId()));

        ArrayList<StorageRelations> entities = new ArrayList<>();
        entities.add(new StorageRelations());
        when(repository.getByStorage(entity.getStorage())).thenReturn(entities);

        dataService.delete(entity);
        verify(storagesDataService, never()).delete(any(Storage.class));
    }
}
