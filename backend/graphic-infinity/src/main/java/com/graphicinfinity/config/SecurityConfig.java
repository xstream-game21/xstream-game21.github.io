package com.graphicinfinity.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
public class SecurityConfig {

    @Bean
    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
        http
            .csrf(csrf -> csrf.disable())
            .authorizeHttpRequests(auth -> auth

                // PUBLIC registration (para sa customers kunwari, test lang if need ni maam)
                .requestMatchers(HttpMethod.POST, "/users").permitAll()

                // ADMIN only (admin users (group members only))
                .requestMatchers("/users/reset-password").hasRole("ADMIN")
                .requestMatchers(HttpMethod.GET, "/users").hasRole("ADMIN")

                // everything else requires login
            .anyRequest().authenticated()
        )
            .httpBasic(Customizer.withDefaults());

        return http.build();
    }

    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }
}