package com.godev.budgetgo.business.storage;

import com.godev.budgetgo.UnitTest;
import com.godev.budgetgo.api.rest.storage.dto.StorageSettingsPatchesDto;
import com.godev.budgetgo.business.storage.impl.StorageSettingsRequestServiceImpl;
import com.godev.budgetgo.domain.storage.StorageRelations;
import com.godev.budgetgo.domain.storage.UserStorageKey;
import com.godev.budgetgo.domain.user.User;
import com.godev.budgetgo.infra.authentication.AuthenticationFacade;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.refEq;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
@UnitTest
class StorageSettingsRequestServiceTest {

    private StorageSettingsRequestService requestService;

    @Mock
    private StoragesRelationsDataService dataService;

    @Mock
    private StorageSettingsConverter converter;

    @Mock
    private AuthenticationFacade authenticationFacade;

    @BeforeEach
    void setUp() {
        requestService = new StorageSettingsRequestServiceImpl(dataService, converter, authenticationFacade);
    }

    @Test
    void patch_general_dataServiceUpdateCall() {
        when(authenticationFacade.getAuthenticatedUser()).thenReturn(new User());
        when(dataService.getById(any(UserStorageKey.class))).thenReturn(new StorageRelations());
        StorageRelations patchedRelations = new StorageRelations();
        when(converter.merge(any(StorageRelations.class), any(StorageSettingsPatchesDto.class))).thenReturn(patchedRelations);

        requestService.patch(1L, new StorageSettingsPatchesDto());
        verify(dataService).update(refEq(patchedRelations));
    }

    @Test
    void deleteByStorageId_general_dataServiceDeleteCall() {
        when(authenticationFacade.getAuthenticatedUser()).thenReturn(new User());
        StorageRelations relations = new StorageRelations();
        when(dataService.getById(any(UserStorageKey.class))).thenReturn(relations);

        requestService.deleteByStorageId(1L);
        verify(dataService).delete(refEq(relations));
    }
}
