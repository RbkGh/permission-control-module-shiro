package com.rodneyboachie.permcontrol.exceptions;

/**
 * author: acerbk
 * Date: 23/02/2022
 * Time: 9:38 pm
 */
public class UnauthorizedException extends RuntimeException {
    public UnauthorizedException(String msg) {
        super(msg);
    }
}
