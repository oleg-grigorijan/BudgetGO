package com.godev.budgetgo.config.swagger;

import lombok.Getter;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.boot.context.properties.ConstructorBinding;

@ConfigurationProperties(prefix = "contact")
@ConstructorBinding
@RequiredArgsConstructor
@Getter
public class ContactProperties {

    private final @NonNull String name;

    private final @NonNull String url;

    private final @NonNull String email;
}
