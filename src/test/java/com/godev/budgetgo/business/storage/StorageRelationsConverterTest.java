package com.godev.budgetgo.business.storage;

import com.godev.budgetgo.UnitTest;
import com.godev.budgetgo.api.rest.storage.dto.ExtendedStorageRelationsCreationDto;
import com.godev.budgetgo.api.rest.storage.dto.StorageRelationsInfoDto;
import com.godev.budgetgo.api.rest.storage.dto.StorageRelationsPatchesDto;
import com.godev.budgetgo.api.rest.user.dto.UserInfoDto;
import com.godev.budgetgo.business.storage.impl.StorageRelationsConverterImpl;
import com.godev.budgetgo.business.user.UsersConverter;
import com.godev.budgetgo.business.user.UsersDataService;
import com.godev.budgetgo.domain.storage.Storage;
import com.godev.budgetgo.domain.storage.StorageRelations;
import com.godev.budgetgo.domain.storage.UserStorageKey;
import com.godev.budgetgo.domain.storage.UserStorageRole;
import com.godev.budgetgo.domain.user.User;
import com.godev.budgetgo.infra.authentication.AuthenticationFacade;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
@UnitTest
class StorageRelationsConverterTest {

    private StorageRelationsConverter converter;

    @Mock
    private StoragesDataService storagesDataService;

    @Mock
    private UsersDataService usersDataService;

    @Mock
    private AuthenticationFacade authenticationFacade;

    @Mock
    private UsersConverter usersConverter;

    @BeforeEach
    void setUp() {
        converter = new StorageRelationsConverterImpl(storagesDataService, usersDataService, authenticationFacade, usersConverter);
    }

    @Test
    void convertToEntity_general_correctReturnValue() {
        ExtendedStorageRelationsCreationDto dto = new ExtendedStorageRelationsCreationDto();
        dto.setUserId(1L);
        dto.setStorageId(2L);
        dto.setUserRole(UserStorageRole.VIEWER);
        assertThat(dto).hasNoNullFieldsOrProperties();

        User user = new User();
        user.setId(dto.getUserId());
        when(usersDataService.getById(dto.getUserId())).thenReturn(user);
        Storage storage = new Storage();
        storage.setId(dto.getStorageId());
        when(storagesDataService.getById(dto.getStorageId())).thenReturn(storage);
        User authenticatedUser = new User();
        authenticatedUser.setId(3L);
        when(authenticationFacade.getAuthenticatedUser()).thenReturn(authenticatedUser);

        StorageRelations expectedEntity = new StorageRelations();
        expectedEntity.setId(new UserStorageKey(dto.getUserId(), dto.getStorageId()));
        expectedEntity.setUser(user);
        expectedEntity.setStorage(storage);
        expectedEntity.setInvitation(true);
        expectedEntity.setIncludedInUserStatistics(false);
        expectedEntity.setInviter(authenticatedUser);
        expectedEntity.setUserRole(dto.getUserRole());
        assertThat(expectedEntity).hasNoNullFieldsOrProperties();

        assertThat(converter.convertToEntity(dto)).isEqualToComparingFieldByField(expectedEntity);
    }

    @Test
    void convertToDto_general_correctReturnValue() {
        StorageRelations entity = new StorageRelations();
        entity.setUser(new User());
        entity.getUser().setId(1L);
        entity.setStorage(new Storage());
        entity.getStorage().setId(2L);
        entity.setId(new UserStorageKey(entity.getUser().getId(), entity.getStorage().getId()));
        entity.setUserRole(UserStorageRole.VIEWER);
        entity.setInviter(new User());
        entity.getInviter().setId(3L);
        entity.setInvitation(true);
        entity.setIncludedInUserStatistics(false);
        assertThat(entity).hasNoNullFieldsOrProperties();

        UserInfoDto userInfoDto = new UserInfoDto();
        userInfoDto.setId(entity.getUser().getId());
        when(usersConverter.convertToDto(entity.getUser())).thenReturn(userInfoDto);
        UserInfoDto inviterInfoDto = new UserInfoDto();
        inviterInfoDto.setId(entity.getInviter().getId());
        when(usersConverter.convertToDto(entity.getInviter())).thenReturn(inviterInfoDto);

        StorageRelationsInfoDto expectedDto = new StorageRelationsInfoDto();
        expectedDto.setUserInfoDto(userInfoDto);
        expectedDto.setUserRole(entity.getUserRole());
        expectedDto.setInviterInfoDto(inviterInfoDto);
        assertThat(expectedDto).hasNoNullFieldsOrProperties();

        assertThat(converter.convertToDto(entity)).isEqualToComparingFieldByField(expectedDto);
    }

    @Test
    void merge_general_correctReturnValue() {
        StorageRelations entity = new StorageRelations();
        entity.setUser(new User());
        entity.getUser().setId(1L);
        entity.setStorage(new Storage());
        entity.getStorage().setId(2L);
        entity.setId(new UserStorageKey(entity.getUser().getId(), entity.getStorage().getId()));
        entity.setUserRole(UserStorageRole.VIEWER);
        entity.setInviter(new User());
        entity.getInviter().setId(3L);
        entity.setInvitation(true);
        entity.setIncludedInUserStatistics(false);
        assertThat(entity).hasNoNullFieldsOrProperties();

        StorageRelationsPatchesDto dto = new StorageRelationsPatchesDto();
        dto.setUserRole(UserStorageRole.EDITOR);

        StorageRelations expectedEntity = entity.cloneShallow();
        expectedEntity.setUserRole(dto.getUserRole().get());
        assertThat(expectedEntity).hasNoNullFieldsOrProperties();

        assertThat(converter.merge(entity, dto)).isEqualToComparingFieldByField(expectedEntity);
    }

    @Test
    void merge_emptyPatchesDto_noEntityChanges() {
        StorageRelations entity = new StorageRelations();
        entity.setUser(new User());
        entity.getUser().setId(1L);
        entity.setStorage(new Storage());
        entity.getStorage().setId(2L);
        entity.setId(new UserStorageKey(entity.getUser().getId(), entity.getStorage().getId()));
        entity.setUserRole(UserStorageRole.VIEWER);
        entity.setInviter(new User());
        entity.getInviter().setId(3L);
        entity.setInvitation(true);
        entity.setIncludedInUserStatistics(false);
        assertThat(entity).hasNoNullFieldsOrProperties();

        assertThat(converter.merge(entity, new StorageRelationsPatchesDto())).isEqualToComparingFieldByField(entity);
    }
}
