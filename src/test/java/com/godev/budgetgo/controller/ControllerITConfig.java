package com.godev.budgetgo.controller;

import com.godev.budgetgo.authentication.AuthenticationFacade;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;

import static org.mockito.Mockito.mock;

@Configuration
class ControllerITConfig {

    @Bean
    @Primary
    public AuthenticationFacade authenticationFacade() {
        return mock(AuthenticationFacade.class);
    }
}
