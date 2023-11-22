package com.global.users.web.api;

import org.junit.jupiter.api.Test;
import org.springframework.http.HttpStatus;

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