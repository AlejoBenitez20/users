package com.global.users.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

import java.util.List;
import java.util.UUID;

@Data
public class LoginResponseDto {

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

    @JsonProperty("name")
    private String name = null;

    @JsonProperty("email")
    private String email = null;

    @JsonProperty("password")
    private String password = null;

    @JsonProperty("phones")
    private List<PhoneDto> phoneDtos = null;

}
