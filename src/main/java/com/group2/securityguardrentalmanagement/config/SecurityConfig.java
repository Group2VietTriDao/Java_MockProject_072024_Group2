package com.group2.securityguardrentalmanagement.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.csrf.CookieCsrfTokenRepository;

@Configuration
@EnableWebSecurity
public class SecurityConfig {

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        http
                .csrf(csrf -> csrf.disable())
                // Sử dụng CSRF Token Repository
                .authorizeHttpRequests(authz -> authz
                       // .requestMatchers("/api/roles/**").hasRole("ADMIN")
                       // .anyRequest().authenticated()
                        .anyRequest().permitAll());
        // Build and return the SecurityFilterChain
        return http.build();
    }
}
