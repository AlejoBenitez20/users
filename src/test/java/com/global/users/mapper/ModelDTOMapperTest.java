package com.global.users.mapper;

import com.global.users.dto.PhoneDto;
import com.global.users.dto.SignUpRequestDto;
import com.global.users.model.PhoneModel;
import com.global.users.model.UserModel;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

import static org.junit.jupiter.api.Assertions.*;

@ExtendWith(MockitoExtension.class)
class ModelDTOMapperTest {

    @InjectMocks
    private ModelDTOMapper modelDTOMapper;

    @Test
    void fromSignUpRequestDtoToUserModel() {

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

        // when
        final var result = modelDTOMapper.fromSignUpRequestDtoToUserModel(signUpRequestDto);

        // Then
        assertNotNull(result);

    }

    @Test
    void fromUserModelAndPhoneModelToLoginResponseDto() {

        // Given
        UserModel userModel = new UserModel();
        userModel.setId(UUID.randomUUID());
        userModel.setName("Juan");
        userModel.setEmail("alejo@gmail.com");
        userModel.setPassword("a2asfGfdfdf4");
        userModel.setCreated("03-03-2023");
        userModel.setLastLogin("03-03-2023");
        userModel.setIsActive(true);

        // When
        final var result = modelDTOMapper.fromUserModelAndPhoneModelToLoginResponseDto(userModel, "dasdsds");

        // Then
        assertNotNull(result);

    }

    @Test
    void fromPhoneModelToPhoneDto() {

        // Given
        List<PhoneModel> phoneModelList = new ArrayList<>();
        PhoneModel phoneModel = new PhoneModel();
        phoneModel.setId(UUID.randomUUID());
        phoneModel.setNumber(3214287);
        phoneModel.setCityCode(1);
        phoneModel.setContryCode("12");
        phoneModelList.add(phoneModel);

        // When
        final var result = modelDTOMapper.fromPhoneModelToPhoneDto(phoneModelList);

        // Then
        assertNotNull(result);

    }

    @Test
    void fromSignUpRequestDtoToUserModelError() {

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

        // when

        // Then
        assertThrows(Exception.class,() -> {
            modelDTOMapper.fromSignUpRequestDtoToUserModel(null);
        });

    }

    @Test
    void fromUserModelAndPhoneModelToLoginResponseDtoError() {

        // Given
        UserModel userModel = new UserModel();
        userModel.setId(UUID.randomUUID());
        userModel.setName("Juan");
        userModel.setEmail("alejo@gmail.com");
        userModel.setPassword("a2asfGfdfdf4");
        userModel.setCreated("03-03-2023");
        userModel.setLastLogin("03-03-2023");
        userModel.setIsActive(true);

        List<PhoneModel> phoneModelList = new ArrayList<>();
        PhoneModel phoneModel = new PhoneModel();
        phoneModel.setId(UUID.randomUUID());
        phoneModel.setNumber(3214287);
        phoneModel.setCityCode(1);
        phoneModel.setContryCode("12");
        phoneModelList.add(phoneModel);

        // When

        // Then
        assertThrows(Exception.class,() -> {
            modelDTOMapper.fromUserModelAndPhoneModelToLoginResponseDto(null, null);
        });

    }

    @Test
    void fromPhoneModelToPhoneDtoError() {

        // Given
        List<PhoneModel> phoneModelList = new ArrayList<>();
        PhoneModel phoneModel = new PhoneModel();
        phoneModel.setId(UUID.randomUUID());
        phoneModel.setNumber(3214287);
        phoneModel.setCityCode(1);
        phoneModel.setContryCode("12");
        phoneModelList.add(phoneModel);

        // When

        // Then
        assertThrows(Exception.class,() -> {
            modelDTOMapper.fromPhoneModelToPhoneDto(null);
        });

    }

}