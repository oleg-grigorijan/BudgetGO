package com.godev.budgetgo.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.transaction.annotation.EnableTransactionManagement;

@Configuration
@EnableJpaRepositories(basePackages = "com.godev.budgetgo.repository")
@EnableTransactionManagement
public class PersistenceConfig {
}
