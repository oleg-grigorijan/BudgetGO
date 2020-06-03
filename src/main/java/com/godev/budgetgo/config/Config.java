package com.godev.budgetgo.config;

import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.module.SimpleModule;
import com.godev.budgetgo.infra.jackson.LocalDateDeserializer;
import com.godev.budgetgo.infra.jackson.LocalDateSerializer;
import com.godev.budgetgo.infra.jackson.LocalDateTimeDeserializer;
import com.godev.budgetgo.infra.jackson.LocalDateTimeSerializer;
import org.springframework.boot.context.properties.ConfigurationPropertiesScan;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.time.Clock;
import java.time.LocalDate;
import java.time.LocalDateTime;

@Configuration
@ConfigurationPropertiesScan(basePackages = "com.godev.budgetgo")
public class Config {

    @Bean
    public ObjectMapper objectMapper() {
        ObjectMapper objectMapper = new ObjectMapper();
        SimpleModule objectMapperModule = new SimpleModule();

        objectMapperModule.addSerializer(new LocalDateSerializer());
        objectMapperModule.addDeserializer(LocalDate.class, new LocalDateDeserializer());

        objectMapperModule.addSerializer(new LocalDateTimeSerializer());
        objectMapperModule.addDeserializer(LocalDateTime.class, new LocalDateTimeDeserializer());

        objectMapper.configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false);

        objectMapper.registerModule(objectMapperModule);

        return objectMapper;
    }

    @Bean
    public Clock clock() {
        return Clock.systemUTC();
    }
}
