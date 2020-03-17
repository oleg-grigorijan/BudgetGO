package com.godev.budgetgo.config.swagger;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.boot.context.properties.ConfigurationProperties;

@ConfigurationProperties(prefix = "contact")
@NoArgsConstructor
@Getter
@Setter
public class ContactProperties {

    private String name;

    private String url;

    private String email;
}
