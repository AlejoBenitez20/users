package com.global.users.service.serv;

import com.global.users.dto.LoginResponseDto;
import com.global.users.dto.SignUpRequestDto;
import com.global.users.dto.SignUpResponseDto;

public interface AuthenticationService {

    SignUpResponseDto signUp(SignUpRequestDto signUpRequestDto);

    LoginResponseDto login(String token);

}
