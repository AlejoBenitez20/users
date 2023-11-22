package com.global.users.service.impl;

import com.global.users.constans.FieldsConstans;
import com.global.users.dto.LoginResponseDto;
import com.global.users.dto.SignUpRequestDto;
import com.global.users.dto.SignUpResponseDto;
import com.global.users.exception.UserExceptions;
import com.global.users.mapper.ModelDTOMapper;
import com.global.users.model.UserModel;
import com.global.users.repository.UsersRepository;
import com.global.users.security.JwtTokenProvider;
import com.global.users.service.serv.AuthenticationService;
import com.global.users.utils.Date;
import com.global.users.utils.FieldsValidations;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Slf4j
@AllArgsConstructor
public class AuthenticationImpl implements AuthenticationService {

    private FieldsValidations fieldsValidations;

    private ModelDTOMapper modelDTOMapper;

    private UsersRepository usersRepository;

    private JwtTokenProvider jwtTokenProvider;

    @Transactional
    @Override
    public SignUpResponseDto signUp( SignUpRequestDto signUpRequestDto ) {

        log.info("[AuthenticationImpl][signUp] Begin process sign Up for user: {} ",signUpRequestDto.getEmail());

        validateData( signUpRequestDto );

        final var resultSaveUser = usersRepository.save( modelDTOMapper.fromSignUpRequestDtoToUserModel( signUpRequestDto ) );

        return buildResponseSignUp( resultSaveUser, signUpRequestDto );

    }

    private void validateData( SignUpRequestDto signUpRequestDto ) {

        fieldsValidations.emptyFieldValidate( FieldsConstans.EMAIL, signUpRequestDto.getEmail() );
        fieldsValidations.emptyFieldValidate( FieldsConstans.PASSWORD, signUpRequestDto.getPassword() );
        fieldsValidations.emailValidate( signUpRequestDto.getEmail() );
        fieldsValidations.passValidate( signUpRequestDto.getPassword() );
        validateIsUserExist( signUpRequestDto.getEmail() );

        log.info("[AuthenticationImpl][validateData] Data validated successful. ");

    }

    private SignUpResponseDto buildResponseSignUp( UserModel userModel,SignUpRequestDto signUpRequestDto ){

        SignUpResponseDto signUpResponseDto = new SignUpResponseDto();
        signUpResponseDto.setId( userModel.getId() );
        signUpResponseDto.setCreated( userModel.getCreated() );
        signUpResponseDto.setLastLogin("");
        signUpResponseDto.setToken( jwtTokenProvider.generateToken(signUpRequestDto.getEmail()) );
        signUpResponseDto.setIsActive( userModel.getIsActive() );

        log.info("[AuthenticationImpl][buildResponseSignUp] build response signUp successful. ");

        return signUpResponseDto;

    }

    private void validateIsUserExist(String email){

        log.info("[AuthenticationImpl][validateIsUserExist] Validating if user exist: {} ",email);

        final var resultUser = usersRepository.findByEmail(email);

        if(resultUser.isPresent())
            throw new UserExceptions( HttpStatus.BAD_REQUEST, "User "+email+" already exists.", 400);

    }

    @Override
    public LoginResponseDto login(String token) {

        log.info("[AuthenticationImpl][login] Begin process login. ");

        final var userData = usersRepository.findByEmail(jwtTokenProvider
                .getUsernameFromToken(token))
                .orElseThrow(() ->
                        new UserExceptions( HttpStatus.BAD_REQUEST, "An error has ocurred while find user by email (login).", 400));

        UserModel userDataUpd = userData;

        updateLastLoginDate( userDataUpd );

        return modelDTOMapper.fromUserModelAndPhoneModelToLoginResponseDto(userData, token);

    }

    private void updateLastLoginDate(UserModel userModel){

        log.info("[AuthenticationImpl][savePhones] Updating las login date user: {} ",userModel.getEmail());

        userModel.setLastLogin(Date.getDateNow());

        try {

            usersRepository.save(userModel);

        }catch (Exception e){

            throw new UserExceptions( HttpStatus.BAD_REQUEST, "An error has ocurred while update data user (updateLastLoginDate).", 400);

        }

    }

}
