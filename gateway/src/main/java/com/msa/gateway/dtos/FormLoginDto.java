package com.msa.gateway.dtos;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Getter;
import lombok.Setter;

@Getter @Setter
public class FormLoginDto {

    @JsonProperty("userid")
    String id;

    @JsonProperty("password")
    String password;
}
