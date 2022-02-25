package com.rodneyboachie.permcontrol.security;

import org.apache.shiro.authc.AuthenticationToken;

/**
 * author: acerbk
 * Date: 24/02/2022
 * Time: 1:03 am
 */
public class JWTToken implements AuthenticationToken {

    private String token;

    public JWTToken(String token) {
        this.token = token;
    }

    @Override
    public Object getPrincipal() {
        return token;
    }

    @Override
    public Object getCredentials() {
        return token;
    }
}
