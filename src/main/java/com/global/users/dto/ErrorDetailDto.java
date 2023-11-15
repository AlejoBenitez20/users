package com.global.users.dto;

import lombok.Getter;
import lombok.Setter;

import java.sql.Timestamp;

@Getter
@Setter
public class ErrorDetailDto {

    private Timestamp timestamp;

    private Integer codigo;

    private String detail;

}
