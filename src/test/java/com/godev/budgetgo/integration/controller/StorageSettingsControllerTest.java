package com.godev.budgetgo.integration.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.datatype.jdk8.Jdk8Module;
import com.godev.budgetgo.authentication.AuthenticationFacade;
import com.godev.budgetgo.converter.StorageSettingsConverter;
import com.godev.budgetgo.data.StoragesRelationsDataService;
import com.godev.budgetgo.data.UsersDataService;
import com.godev.budgetgo.dto.StorageSettingsPatchesDto;
import com.godev.budgetgo.entity.StorageRelations;
import com.godev.budgetgo.entity.User;
import com.godev.budgetgo.entity.UserStorageKey;
import com.godev.budgetgo.exception.StorageRelationsNotFoundException;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.jdbc.Sql;
import org.springframework.transaction.annotation.Transactional;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatCode;
import static org.assertj.core.api.Assertions.assertThatThrownBy;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.delete;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.patch;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

class StorageSettingsControllerTest extends AbstractControllerTest {

    private ObjectMapper objectMapper = new ObjectMapper();

    @Autowired
    private StoragesRelationsDataService relationsDataService;

    @Autowired
    private UsersDataService usersDataService;

    @Autowired
    private StorageSettingsConverter storageSettingsConverter;

    @Autowired
    private AuthenticationFacade authenticationFacade;

    StorageSettingsControllerTest() {
        objectMapper.registerModule(new Jdk8Module());
    }

    @Test
    @Sql({"/sql/reset_tables.sql", "/sql/insert_currencies.sql", "/sql/insert_users.sql", "/sql/insert_storages_and_relations.sql"})
    void getByStorageId_general_isOk() throws Exception {
        long storageId = 1L;

        User authenticatedUser = usersDataService.getById(1L);
        when(authenticationFacade.getAuthenticatedUser()).thenReturn(authenticatedUser);

        mvc.perform(get(API_BASE_URL + "/storages/" + storageId + "/settings"))
                .andDo(print())
                .andExpect(status().isOk());
    }

    @Test
    @Sql({"/sql/reset_tables.sql", "/sql/insert_currencies.sql", "/sql/insert_users.sql"})
    void getByStorageId_nonExistingEntity_isNotFound() throws Exception {
        long storageId = 9999L;

        User authenticatedUser = usersDataService.getById(3L);
        when(authenticationFacade.getAuthenticatedUser()).thenReturn(authenticatedUser);

        assertThatThrownBy(() -> relationsDataService.getById(new UserStorageKey(authenticatedUser.getId(), storageId)))
                .isInstanceOf(StorageRelationsNotFoundException.class);

        mvc.perform(get(API_BASE_URL + "/storages/" + storageId + "/settings"))
                .andDo(print())
                .andExpect(status().isNotFound());
    }

    @Test
    @Transactional
    @Sql({"/sql/reset_tables.sql", "/sql/insert_currencies.sql", "/sql/insert_users.sql", "/sql/insert_storages_and_relations.sql"})
    void patch_general_isOkAndCorrectUpdatingInDb() throws Exception {
        long storageId = 2L;

        User authenticatedUser = usersDataService.getById(3L);
        when(authenticationFacade.getAuthenticatedUser()).thenReturn(authenticatedUser);

        StorageSettingsPatchesDto patchesDto = new StorageSettingsPatchesDto();
        patchesDto.setInvitation(false);
        patchesDto.setIncludedInUserStatistics(true);

        UserStorageKey userStorageKey = new UserStorageKey(authenticatedUser.getId(), storageId);
        StorageRelations expectedEntity = storageSettingsConverter.merge(relationsDataService.getById(userStorageKey), patchesDto);

        mvc.perform(patch(API_BASE_URL + "/storages/" + storageId + "/settings")
                .contentType(APPLICATION_JSON_UTF8)
                .content(objectMapper.writeValueAsString(patchesDto)))
                .andDo(print())
                .andExpect(status().isOk());

        assertThat(relationsDataService.getById(userStorageKey)).isEqualToComparingFieldByField(expectedEntity);
    }

    @Test
    @Sql({"/sql/reset_tables.sql", "/sql/insert_currencies.sql", "/sql/insert_users.sql", "/sql/insert_storages_and_relations.sql"})
    void deleteByStorageId_general_isNoContentAndCorrectDeletingInDb() throws Exception {
        long storageId = 1L;

        User authenticatedUser = usersDataService.getById(1L);
        when(authenticationFacade.getAuthenticatedUser()).thenReturn(authenticatedUser);

        UserStorageKey userStorageKey = new UserStorageKey(authenticatedUser.getId(), storageId);

        assertThatCode(() -> relationsDataService.getById(userStorageKey)).doesNotThrowAnyException();

        mvc.perform(delete(API_BASE_URL + "/storages/" + storageId + "/settings"))
                .andDo(print())
                .andExpect(status().isNoContent());

        assertThatThrownBy(() -> relationsDataService.getById(userStorageKey))
                .isInstanceOf(StorageRelationsNotFoundException.class);
    }
}
