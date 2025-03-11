package com.clava1096.musicstreaming.models.dto.security;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class RegistrationResponse {

    private String message;

    @Override
    public String toString() {
        return "RegistrationResponse [message=" + message + "]";
    }
}