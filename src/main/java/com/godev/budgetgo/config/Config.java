package com.godev.budgetgo.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;

import java.time.Clock;

@Configuration
@EnableWebMvc
@ComponentScan(basePackages = "com.godev.budgetgo")
public class Config {

    @Bean
    public Clock clock() {
        return Clock.systemUTC();
    }
}
