package com.codecool.simplenotes.config;

import com.codecool.simplenotes.config.jwt.AuthEntryPointJwt;
import com.codecool.simplenotes.config.jwt.JwtAuthFilter;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.Ordered;
import org.springframework.core.annotation.Order;
import org.springframework.http.HttpMethod;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import org.springframework.web.cors.CorsConfiguration;

import java.util.List;

@Configuration
@EnableWebSecurity
@Order(Ordered.HIGHEST_PRECEDENCE)
@RequiredArgsConstructor
public class SecurityConfig {

    private final JwtAuthFilter jwtAuthFilter;

    private final AuthenticationProvider authenticationProvider;

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http, AuthEntryPointJwt unauthorizedHandler) throws Exception{
        http
            .cors().configurationSource(request -> {
                var cors = new CorsConfiguration();
                cors.setAllowedOrigins(List.of("http://localhost:5173", "http://localhost:5174"));
                cors.setAllowedMethods(List.of("GET","POST", "PUT", "DELETE", "OPTIONS"));
                cors.setAllowedHeaders(List.of("*"));
                return cors;
            }).and()
            .csrf(csrf -> csrf.disable())
            .exceptionHandling().authenticationEntryPoint(unauthorizedHandler)
            .and()
            .authorizeHttpRequests()
            .requestMatchers(HttpMethod.POST, "/api/auth/**")
                .permitAll()
            .anyRequest()
                .authenticated()
            .and()
                .sessionManagement()
                    .sessionCreationPolicy(SessionCreationPolicy.STATELESS)
            .and()
                .authenticationProvider(authenticationProvider)
            .addFilterAfter(jwtAuthFilter, UsernamePasswordAuthenticationFilter.class);
        return http.build();
    }

}
