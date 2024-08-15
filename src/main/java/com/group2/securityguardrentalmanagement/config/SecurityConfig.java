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
<<<<<<< HEAD
                .csrf(csrf -> csrf.disable())
                // Sử dụng CSRF Token Repository
                .authorizeHttpRequests(authz -> authz
                       // .requestMatchers("/api/roles/**").hasRole("ADMIN")
                       // .anyRequest().authenticated()
                        .anyRequest().permitAll()
                    )
=======
                // Configure CSRF protection using a cookie-based CSRF token repository
                .csrf(csrf -> csrf.csrfTokenRepository(CookieCsrfTokenRepository.withHttpOnlyFalse()))

                // Authorize requests
                .authorizeHttpRequests(authz -> authz
                        // Only users with the ADMIN role can access endpoints under /api/roles/**
                        .requestMatchers("/api/roles/**").hasRole("ADMIN")

                        // All other requests require authentication
                        .anyRequest().authenticated()
                )

                // Configure session management to be stateless (suitable for REST APIs)
>>>>>>> 4d78c45a2805e3c67a3fd102e1f781e0813b8e6c
                .sessionManagement(sess -> sess
                        .sessionCreationPolicy(SessionCreationPolicy.STATELESS)
                );

        // Build and return the SecurityFilterChain
        return http.build();
    }
}
