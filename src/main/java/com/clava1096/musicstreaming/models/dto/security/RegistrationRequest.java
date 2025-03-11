package com.clava1096.musicstreaming.models.dto.security;

//import jakarta.validation.constraints.Email;
//import jakarta.validation.constraints.NotEmpty;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
@NoArgsConstructor
public class RegistrationRequest {
    private String name;
    private String email;
    private String username;
    //@NotEmpty(message = "{registration_password_not_empty}")
    private String password;



}
