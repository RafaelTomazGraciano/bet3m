package com.bet3m.betUenp.security;

import org.springframework.security.core.GrantedAuthority;

import java.util.Collection;
import org.springframework.security.authentication.AbstractAuthenticationToken;

public class ApiKeyAuthentication extends AbstractAuthenticationToken {
    private final String apiKey;

    public ApiKeyAuthentication(String apiKey, Collection<? extends GrantedAuthority> authorities) {
        super(authorities);
        this.apiKey = apiKey;
        setAuthenticated(true);
    }

    @Override
    public Object getCredentials(){
        return null;
    }

    @Override
    public Object getPrincipal(){
        return apiKey;
    }
}
