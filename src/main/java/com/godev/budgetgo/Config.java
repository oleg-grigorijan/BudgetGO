package com.godev.budgetgo;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;

import javax.persistence.EntityManager;
import javax.persistence.Persistence;

@Configuration
@EnableWebMvc
@ComponentScan(basePackages = "com.godev.budgetgo")
public class Config {

    @Bean
    public EntityManager getEntityManager() {
        return Persistence.createEntityManagerFactory("PERSISTENCE").createEntityManager();
    }
}
