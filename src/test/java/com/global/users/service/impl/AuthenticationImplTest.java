package com.global.users.service.impl;

import com.global.users.dto.LoginResponseDto;
import com.global.users.dto.PhoneDto;
import com.global.users.dto.SignUpRequestDto;
import com.global.users.exception.UserExceptions;
import com.global.users.mapper.ModelDTOMapper;
import com.global.users.model.PhoneModel;
import com.global.users.model.UserModel;
import com.global.users.repository.PhonesRepository;
import com.global.users.repository.UsersRepository;
import com.global.users.security.JwtTokenProvider;
import com.global.users.utils.FieldsValidations;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.mockito.InjectMocks;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class AuthenticationImplTest {

    @InjectMocks
    private AuthenticationImpl authenticationService;

    @Mock
    private ModelDTOMapper modelDTOMapper;

    @Mock
    private UsersRepository usersRepository;

    @Mock
    private PhonesRepository phonesRepository;

    @Mock
    private JwtTokenProvider jwtTokenProvider;

    @Mock
    private FieldsValidations fieldsValidations;

    @Test
    void signUp() {

        // Given
        PhoneDto phoneDto = new PhoneDto();
        phoneDto.setContrycode("12");
        phoneDto.setCitycode(1);
        phoneDto.setNumber(32142873);

        List<PhoneDto> phoneDtoList = new ArrayList<>();
        phoneDtoList.add(phoneDto);

        SignUpRequestDto signUpRequestDto = new SignUpRequestDto();
        signUpRequestDto.setName("juan");
        signUpRequestDto.setEmail("juan@gmail.com");
        signUpRequestDto.setPassword("a2asfGfdfdf4");
        signUpRequestDto.setPhoneDtos(phoneDtoList);

        UserModel userModel = new UserModel();
        userModel.setId(UUID.randomUUID());
        userModel.setName("Juan");
        userModel.setEmail("alejo@gmail.com");
        userModel.setPassword("a2asfGfdfdf4");
        userModel.setCreated("03-03-2023");
        userModel.setLastLogin("03-03-2023");
        userModel.setIsActive(true);

        PhoneModel phoneModel = new PhoneModel();
        phoneModel.setId(UUID.randomUUID());
        phoneModel.setUserId(UUID.randomUUID());
        phoneModel.setNumber(3214287);
        phoneModel.setCityCode(1);
        phoneModel.setContryCode("12");

        String token = "eyJhbGciOiJIUzI1NiJ9.eyJzdWIiOiJhbGJlYWtsMTY2QGhvdG1haWsuY29tIiwiaWF0IjoxNzAwMDEzMjcxLCJleHAiOjE3MDAwMTY4NzF9.TAmWzw3ZS9y7hw5sTcGGabPTgzcvkMifdsXGinhdv3E";

        // When
        when(modelDTOMapper.fromSignUpRequestDtoToUserModel(any())).thenReturn(userModel);
        when(usersRepository.save(any())).thenReturn(userModel);
        when(modelDTOMapper.fromPhoneDtoToPhoneModel(any(), any())).thenReturn(phoneModel);
        when(phonesRepository.save(any())).thenReturn(phoneModel);
        when(jwtTokenProvider.generateToken(any())).thenReturn(token);

        final var result = authenticationService.signUp(signUpRequestDto);

        // Then
        verify(fieldsValidations, times(2)).emptyFieldValidate(any(), any());
        verify(fieldsValidations, times(1)).emailValidate(any());
        verify(fieldsValidations, times(1)).passValidate(any());
        assertNotNull(result);

    }

    @Test
    void login() {

        // Given
        String token = "eyJhbGciOiJIUzI1NiJ9.eyJzdWIiOiJhbGJlYWtsMTY2QGhvdG1haWsuY29tIiwiaWF0IjoxNzAwMDEzMjcxLCJleHAiOjE3MDAwMTY4NzF9.TAmWzw3ZS9y7hw5sTcGGabPTgzcvkMifdsXGinhdv3E";
        UserModel userData = new UserModel();
        userData.setId(UUID.randomUUID());
        userData.setName("Juan");
        userData.setEmail("asdas@ddd.com");
        userData.setPassword("a2asfGfdfdf4");
        userData.setCreated("23-03-2023");
        userData.setLastLogin("23-03-2023");
        userData.setIsActive(true);

        List<PhoneModel> phoneModelList = new ArrayList<>();
        PhoneModel phoneModel = new PhoneModel();
        phoneModel.setId(UUID.randomUUID());
        phoneModel.setUserId(UUID.randomUUID());
        phoneModel.setNumber(3214287);
        phoneModel.setCityCode(1);
        phoneModel.setContryCode("12");
        phoneModelList.add(phoneModel);

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
        loginResponseDto.setToken( token );
        loginResponseDto.setIsActive( userModel.getIsActive() );
        loginResponseDto.setName( userModel.getName() );
        loginResponseDto.setEmail( userModel.getEmail());
        loginResponseDto.setPassword( userModel.getPassword() );

        // when
        when(jwtTokenProvider.getUsernameFromToken(any())).thenReturn("asdas@ddd.com");
        when(usersRepository.findByEmail(any())).thenReturn(Optional.of(userData));
        when(phonesRepository.findByUserId(any())).thenReturn(Optional.of(phoneModelList));
        when(usersRepository.save(any())).thenReturn(userModel);
        when(modelDTOMapper.fromUserModelAndPhoneModelToLoginResponseDto(any(), any(), any())).thenReturn(loginResponseDto);


        final var result = authenticationService.login(token);

        // Then
        assertNotNull(result);

    }

    @Test
    void signUpErrorwhenUserAlreadyExist() {

        // Given
        PhoneDto phoneDto = new PhoneDto();
        phoneDto.setContrycode("12");
        phoneDto.setCitycode(1);
        phoneDto.setNumber(32142873);

        List<PhoneDto> phoneDtoList = new ArrayList<>();
        phoneDtoList.add(phoneDto);

        SignUpRequestDto signUpRequestDto = new SignUpRequestDto();
        signUpRequestDto.setName("juan");
        signUpRequestDto.setEmail("juan@gmail.com");
        signUpRequestDto.setPassword("a2asfGfdfdf4");
        signUpRequestDto.setPhoneDtos(phoneDtoList);

        UserModel userModel = new UserModel();
        userModel.setId(UUID.randomUUID());
        userModel.setName("Juan");
        userModel.setEmail("alejo@gmail.com");
        userModel.setPassword("a2asfGfdfdf4");
        userModel.setCreated("03-03-2023");
        userModel.setLastLogin("03-03-2023");
        userModel.setIsActive(true);

        String token = "eyJhbGciOiJIUzI1NiJ9.eyJzdWIiOiJhbGJlYWtsMTY2QGhvdG1haWsuY29tIiwiaWF0IjoxNzAwMDEzMjcxLCJleHAiOjE3MDAwMTY4NzF9.TAmWzw3ZS9y7hw5sTcGGabPTgzcvkMifdsXGinhdv3E";

        // When
        when(usersRepository.findByEmail(any())).thenReturn(Optional.of(userModel));

        // Then
        assertThrows(UserExceptions.class,() -> {
            authenticationService.signUp(signUpRequestDto);
        });

    }

    @Test
    void signUpErrorWhenSavePhones() {

        // Given
        PhoneDto phoneDto = new PhoneDto();
        phoneDto.setContrycode("12");
        phoneDto.setCitycode(1);
        phoneDto.setNumber(32142873);

        List<PhoneDto> phoneDtoList = new ArrayList<>();
        phoneDtoList.add(phoneDto);

        SignUpRequestDto signUpRequestDto = new SignUpRequestDto();
        signUpRequestDto.setName("juan");
        signUpRequestDto.setEmail("juan@gmail.com");
        signUpRequestDto.setPassword("a2asfGfdfdf4");
        signUpRequestDto.setPhoneDtos(phoneDtoList);

        UserModel userModel = new UserModel();
        userModel.setId(UUID.randomUUID());
        userModel.setName("Juan");
        userModel.setEmail("alejo@gmail.com");
        userModel.setPassword("a2asfGfdfdf4");
        userModel.setCreated("03-03-2023");
        userModel.setLastLogin("03-03-2023");
        userModel.setIsActive(true);

        PhoneModel phoneModel = new PhoneModel();
        phoneModel.setId(UUID.randomUUID());
        phoneModel.setUserId(UUID.randomUUID());
        phoneModel.setNumber(3214287);
        phoneModel.setCityCode(1);
        phoneModel.setContryCode("12");

        String token = "eyJhbGciOiJIUzI1NiJ9.eyJzdWIiOiJhbGJlYWtsMTY2QGhvdG1haWsuY29tIiwiaWF0IjoxNzAwMDEzMjcxLCJleHAiOjE3MDAwMTY4NzF9.TAmWzw3ZS9y7hw5sTcGGabPTgzcvkMifdsXGinhdv3E";

        // When
        when(modelDTOMapper.fromSignUpRequestDtoToUserModel(any())).thenReturn(userModel);
        when(usersRepository.save(any())).thenReturn(userModel);
        when(modelDTOMapper.fromPhoneDtoToPhoneModel(any(), any())).thenReturn(phoneModel);
        when(phonesRepository.save(any())).thenThrow(UserExceptions.class);

        // Then
        assertThrows(UserExceptions.class,() -> {
            authenticationService.signUp(signUpRequestDto);
        });

    }

    @Test
    void loginError() {

        // Given
        String token = "eyJhbGciOiJIUzI1NiJ9.eyJzdWIiOiJhbGJlYWtsMTY2QGhvdG1haWsuY29tIiwiaWF0IjoxNzAwMDEzMjcxLCJleHAiOjE3MDAwMTY4NzF9.TAmWzw3ZS9y7hw5sTcGGabPTgzcvkMifdsXGinhdv3E";
        UserModel userData = new UserModel();
        userData.setId(UUID.randomUUID());
        userData.setName("Juan");
        userData.setEmail("asdas@ddd.com");
        userData.setPassword("a2asfGfdfdf4");
        userData.setCreated("23-03-2023");
        userData.setLastLogin("23-03-2023");
        userData.setIsActive(true);

        List<PhoneModel> phoneModelList = new ArrayList<>();
        PhoneModel phoneModel = new PhoneModel();
        phoneModel.setId(UUID.randomUUID());
        phoneModel.setUserId(UUID.randomUUID());
        phoneModel.setNumber(3214287);
        phoneModel.setCityCode(1);
        phoneModel.setContryCode("12");
        phoneModelList.add(phoneModel);

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
        loginResponseDto.setToken( token );
        loginResponseDto.setIsActive( userModel.getIsActive() );
        loginResponseDto.setName( userModel.getName() );
        loginResponseDto.setEmail( userModel.getEmail());
        loginResponseDto.setPassword( userModel.getPassword() );

        // when
        when(jwtTokenProvider.getUsernameFromToken(any())).thenReturn("asdas@ddd.com");
        when(usersRepository.findByEmail(any())).thenReturn(Optional.of(userData));
        when(usersRepository.save(any())).thenThrow(UserExceptions.class);

        // Then
        assertThrows(UserExceptions.class,() -> {
            authenticationService.login(token);
        });

    }


}