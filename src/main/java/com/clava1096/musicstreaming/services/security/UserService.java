package com.clava1096.musicstreaming.services.security;

import com.clava1096.musicstreaming.models.User;
import com.clava1096.musicstreaming.models.dto.security.AuthenticatedUserDto;
import com.clava1096.musicstreaming.models.dto.security.RegistrationRequest;
import com.clava1096.musicstreaming.models.dto.security.RegistrationResponse;

public interface UserService {

    User findByUsername(String username);

    RegistrationResponse registration(RegistrationRequest registrationRequest);

    AuthenticatedUserDto findAuthenticatedUserByUsername(String username);
}
