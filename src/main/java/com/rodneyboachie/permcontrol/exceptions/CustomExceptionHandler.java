package com.rodneyboachie.permcontrol.exceptions;

import com.rodneyboachie.permcontrol.controllers.dto.BaseResponseDTO;
import org.apache.shiro.authz.AuthorizationException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

/**
 * author: acerbk
 * Date: 23/02/2022
 * Time: 9:43 pm
 */
@RestControllerAdvice
public class CustomExceptionHandler {

    private static final Logger log = LoggerFactory.getLogger(CustomExceptionHandler.class);

    @ResponseStatus(HttpStatus.UNAUTHORIZED)
    @ExceptionHandler(UnauthorizedException.class)
    public BaseResponseDTO handleUnauthorizedUser() {
        return new BaseResponseDTO( "Unauthorized", null);
    }

    @ExceptionHandler
    @ResponseStatus(HttpStatus.FORBIDDEN)
    public BaseResponseDTO handleForbiddenUser(AuthorizationException e) {
        log.error("forbidden", e);
        return new BaseResponseDTO( "Forbidden", null);
    }
}
