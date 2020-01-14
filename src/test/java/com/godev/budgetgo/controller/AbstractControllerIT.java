package com.godev.budgetgo.controller;

import com.godev.budgetgo.IntegrationTest;
import com.godev.budgetgo.config.Config;
import com.godev.budgetgo.config.PersistenceConfig;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;

@ExtendWith(SpringExtension.class)
@ContextConfiguration(classes = {ControllerITConfig.class, PersistenceConfig.class, Config.class})
@WebAppConfiguration
@IntegrationTest
abstract class AbstractControllerIT {

    static final String APPLICATION_JSON_UTF8 = "application/json; charset=utf-8";

    static final String API_BASE_URL = "/api";

    MockMvc mvc;

    @Autowired
    WebApplicationContext webApplicationContext;

    @BeforeEach
    void setUp() {
        mvc = MockMvcBuilders.webAppContextSetup(webApplicationContext).build();
    }
}
