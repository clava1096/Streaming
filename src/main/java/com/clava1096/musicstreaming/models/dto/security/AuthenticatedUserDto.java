package com.clava1096.musicstreaming.models.dto.security;

import com.clava1096.musicstreaming.models.enumpack.UserRole;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class AuthenticatedUserDto {
    private String name;

    private String username;

    private String password;

    private UserRole userRole;

}
