package com.godev.budgetgo.request;

import com.godev.budgetgo.UnitTest;
import com.godev.budgetgo.authorization.StoragesAuthorizationService;
import com.godev.budgetgo.converter.StoragesConverter;
import com.godev.budgetgo.data.StoragesDataService;
import com.godev.budgetgo.data.StoragesRelationsDataService;
import com.godev.budgetgo.dto.StorageCreationDto;
import com.godev.budgetgo.dto.StoragePatchesDto;
import com.godev.budgetgo.entity.Storage;
import com.godev.budgetgo.entity.StorageRelations;
import com.godev.budgetgo.exception.StorageAccessDeniedException;
import com.godev.budgetgo.factory.StorageRelationsFactory;
import com.godev.budgetgo.request.impl.StoragesRequestServiceImpl;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.assertj.core.api.Assertions.assertThatThrownBy;
import static org.mockito.AdditionalAnswers.returnsFirstArg;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.refEq;
import static org.mockito.Mockito.doThrow;
import static org.mockito.Mockito.never;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
@UnitTest
class StoragesRequestServiceTest {

    private StoragesRequestService requestService;

    @Mock
    private StoragesDataService dataService;

    @Mock
    private StoragesRelationsDataService relationsDataService;

    @Mock
    private StoragesConverter converter;

    @Mock
    private StoragesAuthorizationService authorizationService;

    @Mock
    private StorageRelationsFactory relationsFactory;

    @BeforeEach
    void setUp() {
        requestService = new StoragesRequestServiceImpl(dataService, relationsDataService, converter, authorizationService, relationsFactory);
    }

    @Test
    void getById_noStorageAccess_exceptionThrown() {
        when(dataService.getById(any(Long.class))).thenReturn(new Storage());
        doThrow(StorageAccessDeniedException.class).when(authorizationService).authorizeAccess(any(Storage.class));

        assertThatThrownBy(() -> requestService.getById(1L)).isInstanceOf(StorageAccessDeniedException.class);
    }

    @Test
    void create_general_dataServiceAddCallAndRelationsCreation() {
        Storage storage = new Storage();
        when(converter.convertToEntity(any(StorageCreationDto.class))).thenReturn(storage);
        when(dataService.add(any(Storage.class))).then(returnsFirstArg());
        StorageRelations creatorRelations = new StorageRelations();
        when(relationsFactory.createCreatorEntityForStorage(any(Storage.class))).thenReturn(creatorRelations);

        requestService.create(new StorageCreationDto());
        verify(dataService).add(refEq(storage));
        verify(relationsDataService).add(refEq(creatorRelations));
    }

    @Test
    void patch_general_dataServiceUpdateCall() {
        when(dataService.getById(any(Long.class))).thenReturn(new Storage());
        Storage patchedStorage = new Storage();
        when(converter.merge(any(Storage.class), any(StoragePatchesDto.class))).thenReturn(patchedStorage);

        requestService.patch(1L, new StoragePatchesDto());
        verify(dataService).update(patchedStorage);
    }

    @Test
    void patch_noModificationAccess_exceptionThrownAndNoDataServiceUpdateCall() {
        when(dataService.getById(any(Long.class))).thenReturn(new Storage());
        doThrow(StorageAccessDeniedException.class).when(authorizationService).authorizeModificationAccess(any(Storage.class));

        assertThatThrownBy(() -> requestService.patch(1L, new StoragePatchesDto())).isInstanceOf(StorageAccessDeniedException.class);
        verify(dataService, never()).update(any(Storage.class));
    }
}
