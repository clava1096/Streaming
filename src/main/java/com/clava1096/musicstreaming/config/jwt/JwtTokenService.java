package com.clava1096.musicstreaming.config.jwt;

import com.clava1096.musicstreaming.mappers.UserMapper;
import com.clava1096.musicstreaming.models.User;
import com.clava1096.musicstreaming.models.dto.security.AuthenticatedUserDto;
import com.clava1096.musicstreaming.models.dto.security.LoginRequest;
import com.clava1096.musicstreaming.models.dto.security.LoginResponse;
import com.clava1096.musicstreaming.services.security.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class JwtTokenService {
    private final UserService userService;

    private final JwtTokenManager jwtTokenManager;

    private final AuthenticationManager authenticationManager;

    public LoginResponse getLoginResponse(LoginRequest loginRequest) {

        final String username = loginRequest.getUsername();
        final String password = loginRequest.getPassword();

        final UsernamePasswordAuthenticationToken usernamePasswordAuthenticationToken = new UsernamePasswordAuthenticationToken(username, password);

        authenticationManager.authenticate(usernamePasswordAuthenticationToken);

        final AuthenticatedUserDto authenticatedUserDto = userService.findAuthenticatedUserByUsername(username);

        final User user = UserMapper.INSTANCE.convertToUser(authenticatedUserDto);
        final String token = jwtTokenManager.generateToken(user);

        return new LoginResponse(token);
    }
}
