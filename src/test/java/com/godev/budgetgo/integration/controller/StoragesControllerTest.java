package com.godev.budgetgo.integration.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.datatype.jdk8.Jdk8Module;
import com.godev.budgetgo.authentication.AuthenticationFacade;
import com.godev.budgetgo.converter.StoragesConverter;
import com.godev.budgetgo.data.StoragesDataService;
import com.godev.budgetgo.data.StoragesRelationsDataService;
import com.godev.budgetgo.data.UsersDataService;
import com.godev.budgetgo.dto.StorageCreationDto;
import com.godev.budgetgo.dto.StorageInfoDto;
import com.godev.budgetgo.dto.StoragePatchesDto;
import com.godev.budgetgo.entity.Storage;
import com.godev.budgetgo.entity.User;
import com.godev.budgetgo.entity.UserStorageKey;
import com.godev.budgetgo.entity.UserStorageRole;
import com.godev.budgetgo.exception.StorageNotFoundException;
import com.godev.budgetgo.exception.StorageRelationsNotFoundException;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.jdbc.Sql;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.transaction.annotation.Transactional;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.patch;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.header;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

class StoragesControllerTest extends AbstractControllerTest {

    private ObjectMapper objectMapper = new ObjectMapper();

    @Autowired
    private StoragesDataService storagesDataService;

    @Autowired
    private UsersDataService usersDataService;

    @Autowired
    private StoragesRelationsDataService relationsDataService;

    @Autowired
    private StoragesConverter storagesConverter;

    @Autowired
    private AuthenticationFacade authenticationFacade;

    StoragesControllerTest() {
        objectMapper.registerModule(new Jdk8Module());
    }

    @Test
    @Transactional
    @Sql({"/sql/reset_tables.sql", "/sql/insert_currencies.sql", "/sql/insert_users.sql"})
    void create_general_isCreatedAndCorrectSavingInDb() throws Exception {
        User authenticatedUser = usersDataService.getById(1L);
        when(authenticationFacade.getAuthenticatedUser()).thenReturn(authenticatedUser);

        StorageCreationDto creationDto = new StorageCreationDto();
        creationDto.setName("Family budget");
        creationDto.setDescription("Shared storage");
        creationDto.setCurrencyId(1L);
        creationDto.setInitialBalance(3000L);

        MvcResult result = mvc.perform(post(API_BASE_URL + "/storages")
                .contentType(APPLICATION_JSON_UTF8)
                .content(objectMapper.writeValueAsString(creationDto)))
                .andDo(print())
                .andExpect(status().isCreated())
                .andExpect(header().exists("Location"))
                .andReturn();
        StorageInfoDto infoDto = objectMapper.readValue(result.getResponse().getContentAsString(), StorageInfoDto.class);

        Storage expectedEntity = storagesConverter.convertToEntity(creationDto);
        expectedEntity.setId(infoDto.getId());

        assertThat(storagesDataService.getById(infoDto.getId()))
                .isEqualToComparingFieldByField(expectedEntity);
        assertThat(relationsDataService.getById(new UserStorageKey(authenticatedUser.getId(), infoDto.getId())))
                .matches(r -> r.getUserRole() == UserStorageRole.CREATOR);
    }

    @Test
    @Transactional
    @Sql({"/sql/reset_tables.sql", "/sql/insert_currencies.sql", "/sql/insert_users.sql", "/sql/insert_storages_and_relations.sql"})
    void patch_general_isOkAndCorrectUpdatingInDb() throws Exception {
        long storageId = 1L;

        User authenticatedUser = usersDataService.getById(1L);
        when(authenticationFacade.getAuthenticatedUser()).thenReturn(authenticatedUser);

        StoragePatchesDto patchesDto = new StoragePatchesDto();
        patchesDto.setName("Abcd");
        patchesDto.setDescription("efgh");

        Storage expectedEntity = storagesConverter.merge(storagesDataService.getById(storageId), patchesDto);

        mvc.perform(patch(API_BASE_URL + "/storages/" + storageId)
                .contentType(APPLICATION_JSON_UTF8)
                .content(objectMapper.writeValueAsString(patchesDto)))
                .andDo(print())
                .andExpect(status().isOk());

        assertThat(storagesDataService.getById(storageId))
                .isEqualToComparingFieldByField(expectedEntity);
    }

    @Test
    @Sql({"/sql/reset_tables.sql", "/sql/insert_currencies.sql", "/sql/insert_users.sql", "/sql/insert_storages_and_relations.sql"})
    void patch_roleWithoutAuthority_isForbidden() throws Exception {
        long storageId = 2L;

        User authenticatedUser = usersDataService.getById(4L);
        when(authenticationFacade.getAuthenticatedUser()).thenReturn(authenticatedUser);

        assertThat(relationsDataService.getById(new UserStorageKey(authenticatedUser.getId(), storageId)))
                .matches(r -> r.getUserRole() == UserStorageRole.VIEWER);

        mvc.perform(patch(API_BASE_URL + "/storages/" + storageId)
                .contentType(APPLICATION_JSON_UTF8)
                .content("{}"))
                .andDo(print())
                .andExpect(status().isForbidden());
    }

    @Test
    @Sql({"/sql/reset_tables.sql", "/sql/insert_currencies.sql", "/sql/insert_users.sql", "/sql/insert_storages_and_relations.sql"})
    void patch_noStorageAccess_isForbidden() throws Exception {
        long storageId = 2L;

        User authenticatedUser = usersDataService.getById(5L);
        when(authenticationFacade.getAuthenticatedUser()).thenReturn(authenticatedUser);

        assertThatThrownBy(() -> relationsDataService.getById(new UserStorageKey(authenticatedUser.getId(), storageId)))
                .isInstanceOf(StorageRelationsNotFoundException.class);

        mvc.perform(patch(API_BASE_URL + "/storages/" + storageId)
                .contentType(APPLICATION_JSON_UTF8)
                .content("{}"))
                .andDo(print())
                .andExpect(status().isForbidden());
    }

    @Test
    @Sql({"/sql/reset_tables.sql", "/sql/insert_currencies.sql", "/sql/insert_users.sql", "/sql/insert_storages_and_relations.sql"})
    void getById_general_isOkAndCorrectResponseBody() throws Exception {
        long storageId = 1L;

        User authenticatedUser = usersDataService.getById(1L);
        when(authenticationFacade.getAuthenticatedUser()).thenReturn(authenticatedUser);

        mvc.perform(get(API_BASE_URL + "/storages/" + storageId))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.id").value(storageId));
    }

    @Test
    @Sql({"/sql/reset_tables.sql", "/sql/insert_currencies.sql", "/sql/insert_users.sql", "/sql/insert_storages_and_relations.sql"})
    void getById_noStorageAccess_isForbidden() throws Exception {
        long storageId = 2L;

        User authenticatedUser = usersDataService.getById(5L);
        when(authenticationFacade.getAuthenticatedUser()).thenReturn(authenticatedUser);

        assertThatThrownBy(() -> relationsDataService.getById(new UserStorageKey(authenticatedUser.getId(), storageId)))
                .isInstanceOf(StorageRelationsNotFoundException.class);

        mvc.perform(get(API_BASE_URL + "/storages/" + storageId))
                .andDo(print())
                .andExpect(status().isForbidden());
    }

    @Test
    @Sql({"/sql/reset_tables.sql", "/sql/insert_currencies.sql", "/sql/insert_users.sql"})
    void getById_nonExistingEntity_isNotFound() throws Exception {
        long storageId = 9999L;

        User authenticatedUser = usersDataService.getById(1L);
        when(authenticationFacade.getAuthenticatedUser()).thenReturn(authenticatedUser);

        assertThatThrownBy(() -> storagesDataService.getById(storageId)).isInstanceOf(StorageNotFoundException.class);

        mvc.perform(get(API_BASE_URL + "/storages/" + storageId))
                .andDo(print())
                .andExpect(status().isNotFound());
    }
}
