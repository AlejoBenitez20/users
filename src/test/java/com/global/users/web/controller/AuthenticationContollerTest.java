package com.global.users.web.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.global.users.dto.LoginResponseDto;
import com.global.users.dto.SignUpRequestDto;
import com.global.users.dto.SignUpResponseDto;
import com.global.users.model.UserModel;
import com.global.users.service.impl.AuthenticationImpl;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;

import java.util.UUID;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@ExtendWith(MockitoExtension.class)
@ContextConfiguration(classes = {AuthenticationContoller.class})
@WebMvcTest(AuthenticationContoller.class)
class AuthenticationContollerTest {

    @MockBean
    AuthenticationImpl authenticationService;

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private WebApplicationContext webApplicationContext;

    ObjectMapper objectMapper =  new ObjectMapper();

    @BeforeEach
    public void setup(){

        mockMvc = MockMvcBuilders.webAppContextSetup(webApplicationContext).build();

    }

    @Test
    void signUp() throws Exception {

        // Given
        SignUpResponseDto signUpResponseDto =  new SignUpResponseDto();
        signUpResponseDto.setId(UUID.randomUUID());
        signUpResponseDto.setIsActive(true);

        SignUpRequestDto signUpRequestDto = new SignUpRequestDto();
        signUpRequestDto.setName("juan");
        signUpRequestDto.setEmail("juan@gmail.com");
        signUpRequestDto.setPassword("a2asfGfdfdf4");

        // When
        when(authenticationService.signUp(any())).thenReturn(signUpResponseDto);

        // Then
        mockMvc.perform(post("/sign-up")
                .contentType(MediaType.APPLICATION_JSON)
                        .content(new ObjectMapper().writeValueAsBytes(signUpRequestDto)))
                .andExpect(status().isOk());

    }

    @Test
    void login() throws Exception {

        // Given
        UserModel userModel = new UserModel();
        userModel.setId(UUID.randomUUID());
        userModel.setName("Juan");
        userModel.setEmail("alejo@gmail.com");
        userModel.setPassword("a2asfGfdfdf4");
        userModel.setCreated("03-03-2023");
        userModel.setLastLogin("03-03-2023");
        userModel.setIsActive(true);

        LoginResponseDto loginResponseDto = new LoginResponseDto();
        loginResponseDto.setId( userModel.getId() );
        loginResponseDto.setCreated( userModel.getCreated() );
        loginResponseDto.setLastLogin( userModel.getLastLogin() );
        loginResponseDto.setToken( "d1s51d5e" );
        loginResponseDto.setIsActive( userModel.getIsActive() );
        loginResponseDto.setName( userModel.getName() );
        loginResponseDto.setEmail( userModel.getEmail());
        loginResponseDto.setPassword( userModel.getPassword() );


        // When
        when(authenticationService.login(any())).thenReturn(loginResponseDto);

        // Then
        mockMvc.perform(get("/login")
                        .contentType(MediaType.APPLICATION_JSON)
                        .header("Authorization", "dsdededs"))
                .andExpect(status().isOk());

    }
}