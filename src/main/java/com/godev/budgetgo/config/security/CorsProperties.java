package com.godev.budgetgo.config.security;

import lombok.Getter;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.boot.context.properties.ConstructorBinding;

import java.util.List;

@ConfigurationProperties(prefix = "cors")
@ConstructorBinding
@RequiredArgsConstructor
@Getter
public class CorsProperties {

    private final @NonNull List<String> allowedOrigins;

    private final @NonNull List<String> allowedMethods;

    private final @NonNull List<String> allowedHeaders;

    private final boolean allowCredentials;
}
