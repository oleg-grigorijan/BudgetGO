package com.godev.budgetgo.integration.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.godev.budgetgo.converter.UsersConverter;
import com.godev.budgetgo.data.UsersDataService;
import com.godev.budgetgo.dto.UserCreationDto;
import com.godev.budgetgo.dto.UserInfoDto;
import com.godev.budgetgo.entity.User;
import com.godev.budgetgo.exception.UserNotFoundException;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.jdbc.Sql;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.transaction.annotation.Transactional;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.header;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

class UsersControllerTest extends AbstractControllerTest {

    private ObjectMapper objectMapper = new ObjectMapper();

    @Autowired
    private UsersDataService usersDataService;

    @Autowired
    private UsersConverter usersConverter;

    @Test
    @Transactional
    @Sql({"/sql/reset_tables.sql", "/sql/insert_currencies.sql"})
    void create_general_isCreatedAndCorrectSavingInDb() throws Exception {
        UserCreationDto creationDto = new UserCreationDto();
        creationDto.setLogin("smith");
        creationDto.setEmail("smith@gmail.com");
        creationDto.setPassword("password");
        creationDto.setName("Sam");
        creationDto.setSurname("Smith");
        creationDto.setMainCurrencyId(1L);
        creationDto.setEmailPublic(false);

        MvcResult result = mvc.perform(post(API_BASE_URL + "/users")
                .contentType(APPLICATION_JSON_UTF8)
                .content(objectMapper.writeValueAsString(creationDto)))
                .andDo(print())
                .andExpect(status().isCreated())
                .andExpect(header().exists("Location"))
                .andReturn();
        UserInfoDto infoDto = objectMapper.readValue(result.getResponse().getContentAsString(), UserInfoDto.class);

        User expectedEntity = usersConverter.convertToEntity(creationDto);
        expectedEntity.setId(infoDto.getId());

        assertThat(usersDataService.getById(infoDto.getId())).isEqualToIgnoringGivenFields(expectedEntity, "passwordHash");
    }

    @Test
    @Sql({"/sql/reset_tables.sql", "/sql/insert_currencies.sql", "/sql/insert_users.sql"})
    void getByLogin_general_isOkAndCorrectResponseBody() throws Exception {
        String login = "smith";
        mvc.perform(get(API_BASE_URL + "/users")
                .param("login", login))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.login").value(login));
    }

    @Test
    @Sql("/sql/reset_tables.sql")
    void getByLogin_nonExistingEntity_isNotFound() throws Exception {
        String login = "abc";

        assertThatThrownBy(() -> usersDataService.getByLogin(login)).isInstanceOf(UserNotFoundException.class);

        mvc.perform(get(API_BASE_URL + "/users")
                .param("login", login))
                .andDo(print())
                .andExpect(status().isNotFound());
    }

    @Test
    @Sql({"/sql/reset_tables.sql", "/sql/insert_currencies.sql", "/sql/insert_users.sql"})
    void getByEmail_publicEmail_isOkAndCorrectResponseBody() throws Exception {
        String email = "smith@example.com";

        assertThat(usersDataService.getByEmail(email)).matches(User::isEmailPublic);

        mvc.perform(get(API_BASE_URL + "/users")
                .param("email", email))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.email").value(email));
    }

    @Test
    @Sql({"/sql/reset_tables.sql", "/sql/insert_currencies.sql", "/sql/insert_users.sql"})
    void getByEmail_privateEmail_isNoContent() throws Exception {
        String email = "oleg.grigorijan@gmail.com";

        assertThat(usersDataService.getByEmail(email)).matches(u -> !u.isEmailPublic());

        mvc.perform(get(API_BASE_URL + "/users")
                .param("email", email))
                .andDo(print())
                .andExpect(status().isNoContent())
                .andExpect(jsonPath("$").doesNotExist());
    }

    @Test
    @Sql("/sql/reset_tables.sql")
    void getByEmail_nonExistingEntity_isNotFound() throws Exception {
        String email = "abc";

        assertThatThrownBy(() -> usersDataService.getByEmail(email)).isInstanceOf(UserNotFoundException.class);

        mvc.perform(get(API_BASE_URL + "/users")
                .param("email", email))
                .andDo(print())
                .andExpect(status().isNotFound());
    }

    @Test
    @Sql({"/sql/reset_tables.sql", "/sql/insert_currencies.sql", "/sql/insert_users.sql"})
    void getById_general_isOkAndCorrectResponseBody() throws Exception {
        long id = 1L;
        mvc.perform(get(API_BASE_URL + "/users/" + id))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.id").value(id));
    }

    @Test
    @Sql("/sql/reset_tables.sql")
    void getById_nonExistingEntity_isNotFound() throws Exception {
        long id = 9999L;

        assertThatThrownBy(() -> usersDataService.getById(id)).isInstanceOf(UserNotFoundException.class);

        mvc.perform(get(API_BASE_URL + "/users/" + id))
                .andDo(print())
                .andExpect(status().isNotFound());
    }
}
