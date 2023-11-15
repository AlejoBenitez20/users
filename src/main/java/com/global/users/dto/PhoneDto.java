package com.global.users.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class PhoneDto {

    @JsonProperty("number")
    private long number = 0L;

    @JsonProperty("citycode")
    private Integer citycode = null;

    @JsonProperty("contrycode")
    private String contrycode = null;


}
