package com.overwatch.agents.infrastructure.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.convert.converter.Converter;
import org.springframework.http.HttpMethod;
import org.springframework.security.authentication.AbstractAuthenticationToken;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.oauth2.jwt.Jwt;
import org.springframework.security.oauth2.server.resource.authentication.JwtAuthenticationConverter;
import org.springframework.security.web.SecurityFilterChain;

import java.util.Collections;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@Configuration
public class SecurityConfig {

    @Bean
    public SecurityFilterChain securityFilterChain( HttpSecurity http ){
        http
                .csrf(csrf -> csrf.disable())
                .authorizeHttpRequests(auth -> auth
                        .requestMatchers("/actuator/**").permitAll()
                        .requestMatchers(HttpMethod.GET,"/agent/**").hasAnyRole("AGENT, DIRECTOR")
                        .requestMatchers(HttpMethod.POST, "/agent/**").hasRole("DIRECTOR")
                        .requestMatchers(HttpMethod.PUT,"/agent/**").hasRole("DIRECTOR")
                        .requestMatchers(HttpMethod.DELETE,"/agent/**").hasRole("DIRECTOR")
                        .anyRequest().authenticated()
                )
                .oauth2ResourceServer(oauth2 -> {
                   oauth2.jwt(configurer -> configurer.jwtAuthenticationConverter(jwtAuthenticationConverter()));
                });
        return http.build();
    }


    private Converter<Jwt, AbstractAuthenticationToken> jwtAuthenticationConverter(){
        JwtAuthenticationConverter converter = new JwtAuthenticationConverter();
        converter.setJwtGrantedAuthoritiesConverter(new KeycloakResourceConverter("overwatch-system"));
        return converter;
    }




}
