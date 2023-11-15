package com.global.users.dto;

import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
public class ErrorDto {

    private List<ErrorDetailDto> error;

}
