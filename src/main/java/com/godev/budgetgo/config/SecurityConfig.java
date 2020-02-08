package com.godev.budgetgo.config;

import com.godev.budgetgo.authentication.RestAuthenticationEntryPoint;
import com.godev.budgetgo.authentication.RestAuthenticationFailureHandler;
import com.godev.budgetgo.authentication.RestAuthenticationSuccessHandler;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.env.Environment;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.web.AuthenticationEntryPoint;
import org.springframework.security.web.authentication.AuthenticationFailureHandler;
import org.springframework.security.web.authentication.AuthenticationSuccessHandler;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.CorsConfigurationSource;
import org.springframework.web.cors.UrlBasedCorsConfigurationSource;

import java.util.List;

@Configuration
@EnableWebSecurity
@EnableGlobalMethodSecurity(securedEnabled = true)
public class SecurityConfig extends WebSecurityConfigurerAdapter {

    private final Environment env;

    public SecurityConfig(Environment env) {
        this.env = env;
    }

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http.sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS);
        http.csrf().disable();
        http.authorizeRequests()
                .antMatchers("/api/docs/**").permitAll()
                .antMatchers(HttpMethod.GET, "/api/currencies/**").permitAll()
                .antMatchers(HttpMethod.GET, "/api/users/**").permitAll()
                .antMatchers(HttpMethod.POST, "/api/users").permitAll()
                .antMatchers("/api/**").hasRole("USER");
        http.httpBasic();
        http.exceptionHandling().authenticationEntryPoint(authenticationEntryPoint());
        http.formLogin()
                .loginProcessingUrl("/api/login")
                .successHandler(authenticationSuccessHandler())
                .failureHandler(authenticationFailureHandler());
        http.cors().configurationSource(corsConfigurationSource());
    }

    @Bean
    public AuthenticationEntryPoint authenticationEntryPoint() {
        return new RestAuthenticationEntryPoint();
    }

    @Bean
    public AuthenticationSuccessHandler authenticationSuccessHandler() {
        return new RestAuthenticationSuccessHandler();
    }

    @Bean
    public AuthenticationFailureHandler authenticationFailureHandler() {
        return new RestAuthenticationFailureHandler();
    }

    @Bean
    CorsConfigurationSource corsConfigurationSource() {
        CorsConfiguration configuration = new CorsConfiguration();
        configuration.setAllowedOrigins(List.of(env.getRequiredProperty("cors.allowedOrigins", String[].class)));
        configuration.setAllowedMethods(List.of(env.getRequiredProperty("cors.allowedMethods", String[].class)));
        configuration.setAllowedHeaders(List.of(env.getRequiredProperty("cors.allowedHeaders", String[].class)));
        configuration.setAllowCredentials(env.getRequiredProperty("cors.allowCredentials", Boolean.class));
        UrlBasedCorsConfigurationSource source = new UrlBasedCorsConfigurationSource();
        source.registerCorsConfiguration("/**", configuration);
        return source;
    }
}
