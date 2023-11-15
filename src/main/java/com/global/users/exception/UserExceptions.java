package com.global.users.exception;

import lombok.Getter;
import lombok.Setter;
import org.springframework.http.HttpStatus;

@Getter
@Setter
public class UserExceptions extends RuntimeException {

    private HttpStatus status;

    private int code;

    public UserExceptions( HttpStatus status, String message, int code) {
        super(message);
        this.code = code;
        this.status = status;
    }


}
