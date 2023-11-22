package com.global.users.mapper;

import com.global.users.dto.LoginResponseDto;
import com.global.users.dto.PhoneDto;
import com.global.users.dto.SignUpRequestDto;
import com.global.users.exception.UserExceptions;
import com.global.users.model.PhoneModel;
import com.global.users.model.UserModel;
import com.global.users.utils.Date;
import org.springframework.http.HttpStatus;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
public class ModelDTOMapper {

    public UserModel fromSignUpRequestDtoToUserModel(SignUpRequestDto signUpRequestDto){

        UserModel userModel = new UserModel();

        try {

            userModel.setName(signUpRequestDto.getName());
            userModel.setEmail(signUpRequestDto.getEmail());
            userModel.setPassword(new BCryptPasswordEncoder().encode(signUpRequestDto.getPassword()));
            userModel.setCreated(Date.getDateNow());
            userModel.setIsActive(true);

            signUpRequestDto.getPhoneDtos().forEach(phoneDto -> {

                PhoneModel phoneModel = new PhoneModel();
                phoneModel.setNumber( phoneDto.getNumber() );
                phoneModel.setCityCode( phoneDto.getCitycode() );
                phoneModel.setContryCode( phoneDto.getContrycode() );

                userModel.addPhone(phoneModel);

            });

        }catch (Exception e){

            throw new UserExceptions( HttpStatus.BAD_REQUEST, "An error has ocurred while mapper data (fromSignUpRequestDtoToUserModel)." + e, 400);

        }

        return userModel;

    }

    public LoginResponseDto fromUserModelAndPhoneModelToLoginResponseDto(UserModel userModel,  String token){

        LoginResponseDto loginResponseDto = new LoginResponseDto();

        try {

            loginResponseDto.setId( userModel.getId() );
            loginResponseDto.setCreated( userModel.getCreated() );
            loginResponseDto.setLastLogin( userModel.getLastLogin() );
            loginResponseDto.setToken( token );
            loginResponseDto.setIsActive( userModel.getIsActive() );
            loginResponseDto.setName( userModel.getName() );
            loginResponseDto.setEmail( userModel.getEmail());
            loginResponseDto.setPassword( userModel.getPassword() );
            loginResponseDto.setPhoneDtos(fromPhoneModelToPhoneDto(userModel.getPhoneModelList()));

        }catch (Exception e){

            throw new UserExceptions( HttpStatus.BAD_REQUEST, "An error has ocurred while mapper data ( fromUserModelAndPhoneModelToLoginResponseDto ).", 400);

        }

        return loginResponseDto;

    }

    public List<PhoneDto> fromPhoneModelToPhoneDto( List<PhoneModel> phoneModelList ){

        List<PhoneDto> phoneDtoList = new ArrayList<>();

        try {

            phoneModelList.forEach( phoneModel -> {

                PhoneDto phoneDto = new PhoneDto();
                phoneDto.setNumber( phoneModel.getNumber() );
                phoneDto.setCitycode( phoneModel.getCityCode() );
                phoneDto.setContrycode( phoneModel.getContryCode() );

                phoneDtoList.add(phoneDto);

            });

        }catch (Exception e){

            throw new UserExceptions( HttpStatus.BAD_REQUEST, "An error has ocurred while mapper data ( fromPhoneDtoToPhoneModel ).", 400);

        }

        return phoneDtoList;

    }

}
