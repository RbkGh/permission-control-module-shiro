package com.rodneyboachie.permcontrol.security;

import lombok.Data;
import lombok.Getter;
import lombok.ToString;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;
import org.springframework.stereotype.Component;

/**
 * author: acerbk
 * Date: 24/02/2022
 * Time: 12:57 am
 */
@Component
@Data
@ToString
public class JWTConfig {

    @Value("${app.security.jwt.url:/login}")
    private String url;

    @Value("${app.security.jwt.header:Authorization}")
    private String header;

    @Value("${app.security.jwt.prefix:Bearer}")
    private String prefix;

    @Value("${app.security.jwt.expiration:#{24*60*60}}")
    private int expiration; // default 24 hours

    @Value("${app.security.jwt.secret:randomSecret*12$}")
    private String secret;
}
