package com.godev.budgetgo.request;

import com.godev.budgetgo.authorization.StoragesAuthorizationService;
import com.godev.budgetgo.authorization.StoragesRelationsAuthorizationService;
import com.godev.budgetgo.converter.StorageRelationsConverter;
import com.godev.budgetgo.data.StoragesDataService;
import com.godev.budgetgo.data.StoragesRelationsDataService;
import com.godev.budgetgo.dto.ExtendedStorageRelationsCreationDto;
import com.godev.budgetgo.dto.StorageRelationsPatchesDto;
import com.godev.budgetgo.entity.Storage;
import com.godev.budgetgo.entity.StorageRelations;
import com.godev.budgetgo.entity.UserStorageKey;
import com.godev.budgetgo.exception.StorageAccessDeniedException;
import com.godev.budgetgo.exception.StorageRelationsAccessDeniedException;
import com.godev.budgetgo.request.impl.StoragesRelationsRequestServiceImpl;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.assertj.core.api.Assertions.assertThatThrownBy;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.refEq;
import static org.mockito.Mockito.doThrow;
import static org.mockito.Mockito.never;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class StoragesRelationsRequestServiceTest {

    private StoragesRelationsRequestService requestService;

    @Mock
    private StoragesRelationsDataService dataService;

    @Mock
    private StoragesDataService storagesDataService;

    @Mock
    private StorageRelationsConverter converter;

    @Mock
    private StoragesRelationsAuthorizationService authorizationService;

    @Mock
    private StoragesAuthorizationService storagesAuthorizationService;

    @BeforeEach
    void setUp() {
        requestService = new StoragesRelationsRequestServiceImpl(
                dataService,
                storagesDataService,
                converter,
                authorizationService,
                storagesAuthorizationService
        );
    }

    @Test
    void getByStorageId_noStorageAccess_exceptionThrown() {
        when(storagesDataService.getById(any())).thenReturn(new Storage());
        doThrow(StorageAccessDeniedException.class).when(storagesAuthorizationService).authorizeAccess(any(Storage.class));

        assertThatThrownBy(() -> requestService.getByStorageId(1L));
    }

    @Test
    void create_general_dataServiceAddCall() {
        StorageRelations relations = new StorageRelations();
        when(converter.convertToEntity(any(ExtendedStorageRelationsCreationDto.class))).thenReturn(relations);

        requestService.create(new ExtendedStorageRelationsCreationDto());
        verify(dataService).add(refEq(relations));
    }

    @Test
    void create_noCreationAccess_exceptionThrownAndNoDataServiceAddCall() {
        when(converter.convertToEntity(any(ExtendedStorageRelationsCreationDto.class))).thenReturn(new StorageRelations());
        doThrow(StorageRelationsAccessDeniedException.class).when(authorizationService).authorizeCreationAccess(any(StorageRelations.class));

        assertThatThrownBy(() -> requestService.create(new ExtendedStorageRelationsCreationDto()))
                .isInstanceOf(StorageRelationsAccessDeniedException.class);
        verify(dataService, never()).add(any(StorageRelations.class));
    }

    @Test
    void patch_general_dataServiceUpdateCall() {
        when(dataService.getById(any(UserStorageKey.class))).thenReturn(new StorageRelations());
        StorageRelations patchedRelations = new StorageRelations();
        when(converter.merge(any(StorageRelations.class), any(StorageRelationsPatchesDto.class))).thenReturn(patchedRelations);

        requestService.patch(new UserStorageKey(), new StorageRelationsPatchesDto());
        verify(dataService).update(refEq(patchedRelations));
    }

    @Test
    void patch_noModificationAccess_exceptionThrownAndNoDataServiceUpdateCall() {
        when(dataService.getById(any(UserStorageKey.class))).thenReturn(new StorageRelations());
        doThrow(StorageRelationsAccessDeniedException.class)
                .when(authorizationService).authorizeModificationAccess(any(StorageRelations.class), any(StorageRelationsPatchesDto.class));

        assertThatThrownBy(() -> requestService.patch(new UserStorageKey(), new StorageRelationsPatchesDto()))
                .isInstanceOf(StorageRelationsAccessDeniedException.class);
    }

    @Test
    void deleteById_general_dataServiceDeleteCall() {
        StorageRelations relations = new StorageRelations();
        when(dataService.getById(any(UserStorageKey.class))).thenReturn(relations);

        requestService.deleteById(new UserStorageKey());
        verify(dataService).delete(refEq(relations));
    }

    @Test
    void deleteById_noDeletionAccess_exceptionThrownAndNoDataServiceDeleteCall() {
        when(dataService.getById(any(UserStorageKey.class))).thenReturn(new StorageRelations());
        doThrow(StorageRelationsAccessDeniedException.class).when(authorizationService).authorizeDeletionAccess(any(StorageRelations.class));

        assertThatThrownBy(() -> requestService.deleteById(new UserStorageKey()))
                .isInstanceOf(StorageRelationsAccessDeniedException.class);
        verify(dataService, never()).delete(any(StorageRelations.class));
    }
}