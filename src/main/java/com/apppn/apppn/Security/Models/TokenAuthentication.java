package com.apppn.apppn.Security.Models;



import java.util.Collection;

import org.springframework.security.authentication.AbstractAuthenticationToken;
import org.springframework.security.core.GrantedAuthority;

public class TokenAuthentication extends AbstractAuthenticationToken {

    private final String token;
    private Object principal;

    public TokenAuthentication(String token) {
        super(null);
        this.token = token;
        setAuthenticated(false);
    }

    public TokenAuthentication(Collection<? extends GrantedAuthority> authorities, String token, Object princiapl) {
        super(authorities);
        this.token = token;
        setAuthenticated(true);
        this.principal = princiapl;
    }

    @Override
    public Object getCredentials() {
        return token;
    }

    @Override
    public Object getPrincipal() {
        return principal;
    }

    public String getToken() {
        return (String) getCredentials();
    }

}
