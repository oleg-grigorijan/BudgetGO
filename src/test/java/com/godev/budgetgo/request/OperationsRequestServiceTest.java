package com.godev.budgetgo.request;

import com.godev.budgetgo.authorization.StoragesAuthorizationService;
import com.godev.budgetgo.converter.OperationsConverter;
import com.godev.budgetgo.data.OperationsDataService;
import com.godev.budgetgo.data.StoragesDataService;
import com.godev.budgetgo.dto.ExtendedOperationCreationDto;
import com.godev.budgetgo.dto.OperationPatchesDto;
import com.godev.budgetgo.entity.Operation;
import com.godev.budgetgo.entity.Storage;
import com.godev.budgetgo.entity.StorageOperationKey;
import com.godev.budgetgo.exception.StorageAccessDeniedException;
import com.godev.budgetgo.request.impl.OperationsRequestServiceImpl;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.time.LocalDate;

import static org.assertj.core.api.Assertions.assertThatThrownBy;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.refEq;
import static org.mockito.Mockito.doThrow;
import static org.mockito.Mockito.never;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class OperationsRequestServiceTest {

    private OperationsRequestService requestService;

    @Mock
    private OperationsDataService dataService;

    @Mock
    private StoragesDataService storagesDataService;

    @Mock
    private OperationsConverter converter;

    @Mock
    private StoragesAuthorizationService authorizationService;

    @BeforeEach
    void setUp() {
        requestService = new OperationsRequestServiceImpl(dataService, storagesDataService, converter, authorizationService);
    }

    @Test
    void getById_noStorageAccess_exceptionThrown() {
        when(dataService.getById(any(StorageOperationKey.class))).thenReturn(newOperationWithStorage());
        doThrow(StorageAccessDeniedException.class).when(authorizationService).authorizeAccess(any(Storage.class));

        assertThatThrownBy(() -> requestService.getById(new StorageOperationKey())).isInstanceOf(StorageAccessDeniedException.class);
    }

    @Test
    void getByStorageId_noStorageAccess_exceptionThrown() {
        when(storagesDataService.getById(any(Long.class))).thenReturn(new Storage());
        doThrow(StorageAccessDeniedException.class).when(authorizationService).authorizeAccess(any(Storage.class));

        assertThatThrownBy(() -> requestService.getByStorageId(1L)).isInstanceOf(StorageAccessDeniedException.class);
    }

    @Test
    void getByStorageIdAndDateBetween_noStorageAccess_exceptionThrown() {
        when(storagesDataService.getById(any(Long.class))).thenReturn(new Storage());
        doThrow(StorageAccessDeniedException.class).when(authorizationService).authorizeAccess(any(Storage.class));

        assertThatThrownBy(() -> requestService.getByStorageIdAndDateBetween(1L, LocalDate.MIN, LocalDate.MAX))
                .isInstanceOf(StorageAccessDeniedException.class);
    }

    @Test
    void create_general_dataServiceAddCall() {
        Operation operation = new Operation();
        when(converter.convertFromDto(any(ExtendedOperationCreationDto.class))).thenReturn(operation);

        requestService.create(new ExtendedOperationCreationDto());
        verify(dataService).add(refEq(operation));
    }

    @Test
    void create_noStorageAccess_exceptionThrownAndNoDataServiceAddCall() {
        when(converter.convertFromDto(any(ExtendedOperationCreationDto.class))).thenReturn(newOperationWithStorage());
        doThrow(StorageAccessDeniedException.class).when(authorizationService).authorizeModificationAccess(any(Storage.class));

        assertThatThrownBy(() -> requestService.create(new ExtendedOperationCreationDto())).isInstanceOf(StorageAccessDeniedException.class);
        verify(dataService, never()).add(any(Operation.class));
    }

    @Test
    void patch_general_dataServiceUpdateCall() {
        when(dataService.getById(any(StorageOperationKey.class))).thenReturn(new Operation());
        Operation patchedOperation = new Operation();
        when(converter.merge(any(Operation.class), any(OperationPatchesDto.class))).thenReturn(patchedOperation);

        requestService.patch(new StorageOperationKey(), new OperationPatchesDto());
        verify(dataService).update(refEq(patchedOperation));
    }

    @Test
    void patch_noStorageModificationAccess_exceptionThrownAndNoDataServiceUpdateCall() {
        when(dataService.getById(any(StorageOperationKey.class))).thenReturn(newOperationWithStorage());
        doThrow(StorageAccessDeniedException.class).when(authorizationService).authorizeModificationAccess(any(Storage.class));

        assertThatThrownBy(() -> requestService.patch(new StorageOperationKey(), new OperationPatchesDto()))
                .isInstanceOf(StorageAccessDeniedException.class);
        verify(dataService, never()).update(any(Operation.class));
    }

    @Test
    void deleteById_general_dataServiceDeleteCall() {
        Operation operation = new Operation();
        when(dataService.getById(any(StorageOperationKey.class))).thenReturn(operation);

        requestService.deleteById(new StorageOperationKey());
        verify(dataService).delete(refEq(operation));
    }

    @Test
    void deleteById_noStorageModificationAccess_exceptionThrownAndNoDataServiceDeleteCall() {
        when(dataService.getById(any(StorageOperationKey.class))).thenReturn(newOperationWithStorage());
        doThrow(StorageAccessDeniedException.class).when(authorizationService).authorizeModificationAccess(any(Storage.class));

        assertThatThrownBy(() -> requestService.deleteById(new StorageOperationKey())).isInstanceOf(StorageAccessDeniedException.class);
        verify(dataService, never()).delete(any(Operation.class));
    }

    private Operation newOperationWithStorage() {
        Operation operation = new Operation();
        operation.setStorage(new Storage());
        return operation;
    }
}
