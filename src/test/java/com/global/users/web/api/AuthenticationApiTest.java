package com.global.users.web.api;

import com.global.users.dto.LoginResponseDto;
import com.global.users.dto.SignUpRequestDto;
import com.global.users.dto.SignUpResponseDto;
import org.junit.jupiter.api.Test;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import static org.junit.jupiter.api.Assertions.*;

class AuthenticationApiTest {

    @Test
    void testDefaultMethod() {

        AuthenticationApi authenticationApi = new AuthenticationApi() {
        };

        assertEquals(HttpStatus.OK, authenticationApi.login(null).getStatusCode());
        assertEquals(HttpStatus.OK, authenticationApi.signUp(null).getStatusCode());

    }
}