package com.global.users.exception;

import com.global.users.dto.ErrorDetailDto;
import com.global.users.dto.ErrorDto;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;

@RestControllerAdvice
public class ControllerAdvice {

    @ExceptionHandler(value = UserExceptions.class)
    public ResponseEntity<ErrorDto> gpExceptionHandler(UserExceptions ex){

        Timestamp currentTimestamp = getCurrentTimestamp();

        ErrorDto error = new ErrorDto();

        ErrorDetailDto errorDetailDto = new ErrorDetailDto();
        errorDetailDto.setTimestamp(currentTimestamp);
        errorDetailDto.setDetail(ex.getMessage());
        errorDetailDto.setCodigo(ex.getCode());

        List<ErrorDetailDto> errorDetailDtoList = new ArrayList<>();
        errorDetailDtoList.add(errorDetailDto);

        error.setError(errorDetailDtoList);

        return new ResponseEntity<>(error, ex.getStatus());
    }

    public static Timestamp getCurrentTimestamp() {
        return new Timestamp(System.currentTimeMillis());
    }

}
