package com.global.users.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Getter;
import lombok.Setter;

import java.util.UUID;

@Getter
@Setter
public class SignUpResponseDto {

    @JsonProperty("id")
    private UUID id = null;

    @JsonProperty("created")
    private String created = null;

    @JsonProperty("lastLogin")
    private String lastLogin = null;

    @JsonProperty("token")
    private String token = null;

    @JsonProperty("isActive")
    private Boolean isActive = null;


}
