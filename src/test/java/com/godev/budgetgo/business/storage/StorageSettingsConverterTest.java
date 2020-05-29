package com.godev.budgetgo.business.storage;

import com.godev.budgetgo.UnitTest;
import com.godev.budgetgo.api.rest.storage.dto.StorageSettingsInfoDto;
import com.godev.budgetgo.api.rest.storage.dto.StorageSettingsPatchesDto;
import com.godev.budgetgo.api.rest.user.dto.UserInfoDto;
import com.godev.budgetgo.business.storage.impl.StorageSettingsConverterImpl;
import com.godev.budgetgo.business.user.UsersConverter;
import com.godev.budgetgo.domain.storage.Storage;
import com.godev.budgetgo.domain.storage.StorageRelations;
import com.godev.budgetgo.domain.storage.UserStorageKey;
import com.godev.budgetgo.domain.storage.UserStorageRole;
import com.godev.budgetgo.domain.user.User;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
@UnitTest
class StorageSettingsConverterTest {

    private StorageSettingsConverter converter;

    @Mock
    private UsersConverter usersConverter;

    @BeforeEach
    void setUp() {
        converter = new StorageSettingsConverterImpl(usersConverter);
    }

    @Test
    void convertToDto_general_correctReturnValue() {
        StorageRelations entity = new StorageRelations();
        entity.setUser(new User());
        entity.getUser().setId(1L);
        entity.setStorage(new Storage());
        entity.getStorage().setId(2L);
        entity.setId(new UserStorageKey(entity.getUser().getId(), entity.getStorage().getId()));
        entity.setUserRole(UserStorageRole.ADMIN);
        entity.setInvitation(false);
        entity.setIncludedInUserStatistics(true);
        entity.setInviter(new User());
        entity.getInviter().setId(3L);
        assertThat(entity).hasNoNullFieldsOrProperties();

        UserInfoDto inviterInfoDto = new UserInfoDto();
        inviterInfoDto.setId(entity.getInviter().getId());
        when(usersConverter.convertToDto(entity.getInviter())).thenReturn(inviterInfoDto);

        StorageSettingsInfoDto expectedDto = new StorageSettingsInfoDto();
        expectedDto.setUserRole(entity.getUserRole());
        expectedDto.setInvitation(entity.isInvitation());
        expectedDto.setIncludedInUserStatistics(entity.isIncludedInUserStatistics());
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
        entity.setUserRole(UserStorageRole.ADMIN);
        entity.setInvitation(true);
        entity.setIncludedInUserStatistics(false);
        entity.setInviter(new User());
        entity.getInviter().setId(3L);
        assertThat(entity).hasNoNullFieldsOrProperties();

        StorageSettingsPatchesDto dto = new StorageSettingsPatchesDto();
        dto.setInvitation(false);
        dto.setIncludedInUserStatistics(true);

        StorageRelations expectedEntity = entity.cloneShallow();
        expectedEntity.setInvitation(dto.getInvitation().get());
        expectedEntity.setIncludedInUserStatistics(dto.getIncludedInUserStatistics().get());
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
        entity.setUserRole(UserStorageRole.ADMIN);
        entity.setInvitation(true);
        entity.setIncludedInUserStatistics(false);
        entity.setInviter(new User());
        entity.getInviter().setId(3L);
        assertThat(entity).hasNoNullFieldsOrProperties();

        assertThat(converter.merge(entity, new StorageSettingsPatchesDto())).isEqualToComparingFieldByField(entity);
    }
}
