package com.overwatch.agents.infrastructure.config;

import org.springframework.core.convert.converter.Converter;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.oauth2.jwt.Jwt;

import java.util.*;
import java.util.stream.Collectors;

public class KeycloakResourceConverter implements Converter <Jwt, Collection <GrantedAuthority>> {

    private String clientId;

    public KeycloakResourceConverter( String clientId ) {
        this.clientId = clientId;
    }

    @Override
    public Collection <GrantedAuthority> convert( Jwt jwt ) {
        Map <String, Object> resourceAccess = jwt.getClaimAsMap("resource_access");

        if (resourceAccess == null || !resourceAccess.containsKey(clientId)) return Collections.emptyList();

        Map<String,Object> clientResource = (Map <String, Object>) resourceAccess.get(clientId);
        Collection<String> roles = (Collection<String>) clientResource.get("roles");

        return roles.stream().map(role -> new SimpleGrantedAuthority("ROLE_"+role))
                .collect(Collectors.toList());
    }
}
