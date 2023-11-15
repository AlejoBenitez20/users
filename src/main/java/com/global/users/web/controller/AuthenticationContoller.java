package com.global.users.web.controller;

import com.global.users.dto.LoginResponseDto;
import com.global.users.dto.SignUpRequestDto;
import com.global.users.dto.SignUpResponseDto;
import com.global.users.service.serv.AuthenticationService;
import com.global.users.web.api.AuthenticationApi;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class AuthenticationContoller implements AuthenticationApi {

    @Autowired
    private AuthenticationService authenticationService;

    @Override
    public ResponseEntity<SignUpResponseDto> signUp(SignUpRequestDto signUpRequestDto) {
        return new ResponseEntity<>(authenticationService.signUp(signUpRequestDto), HttpStatus.OK);
    }

    @Override
    public ResponseEntity<LoginResponseDto> login(String token) {
        return new ResponseEntity<>(authenticationService.login(token), HttpStatus.OK);
    }
}
