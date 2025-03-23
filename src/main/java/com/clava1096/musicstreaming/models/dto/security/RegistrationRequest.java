package com.clava1096.musicstreaming.models.dto.security;

//import jakarta.validation.constraints.Email;
//import jakarta.validation.constraints.NotEmpty;

import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.validation.constraints.NotBlank;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
@NoArgsConstructor
public class RegistrationRequest {

    @NotBlank
    @JsonProperty(value = "name", required = true)
    private String name;

    @NotBlank
    @JsonProperty(value = "email", required = true)
    private String email;

    @NotBlank
    @JsonProperty(value = "username", required = true)
    private String username;

    @NotBlank
    @JsonProperty(value = "password", required = true)
    private String password;
}
