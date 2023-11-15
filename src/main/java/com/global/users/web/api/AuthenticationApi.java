package com.global.users.web.api;

import com.global.users.dto.LoginResponseDto;
import com.global.users.dto.SignUpRequestDto;
import com.global.users.dto.SignUpResponseDto;
import io.swagger.annotations.ApiOperation;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

public interface AuthenticationApi {

    @ApiOperation(value = "Sign-up user", nickname = "signUp", notes = "This api Sign-up user.", response = SignUpResponseDto.class)
    @RequestMapping(value = "/sign-up",
            produces = {"application/json"},
            method = RequestMethod.POST)
    default ResponseEntity<SignUpResponseDto> signUp(@RequestBody SignUpRequestDto signUpRequestDto){

        return new ResponseEntity<>(HttpStatus.OK);
    }

    @ApiOperation(value = "Login user", nickname = "login", notes = "This api authentic user.", response = LoginResponseDto.class)
    @RequestMapping(value = "/login",
            produces = {"application/json"},
            method = RequestMethod.GET)
    default ResponseEntity<LoginResponseDto> login(@RequestHeader("Authorization") String authorizationHeader){

        return new ResponseEntity<>(HttpStatus.OK);
    }



}
