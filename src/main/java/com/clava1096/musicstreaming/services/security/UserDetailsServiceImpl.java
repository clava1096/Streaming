package com.clava1096.musicstreaming.services.security;

import com.clava1096.musicstreaming.models.dto.security.AuthenticatedUserDto;
import com.clava1096.musicstreaming.models.enumpack.UserRole;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.Collections;
import java.util.Objects;

@Service
@RequiredArgsConstructor
public class UserDetailsServiceImpl implements UserDetailsService {
    private static final String USERNAME_OR_PASSWORD_INVALID = "Invalid username or password.";

    private final UserService userService;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        final AuthenticatedUserDto authenticatedUser = userService.findAuthenticatedUserByUsername(username);

        if(Objects.isNull(authenticatedUser)) {
            throw new UsernameNotFoundException(USERNAME_OR_PASSWORD_INVALID);
        }

        final String authenticatedUsername = authenticatedUser.getUsername();
        final String authenticatedPassword = authenticatedUser.getPassword();
        final UserRole userRole = authenticatedUser.getUserRole();
        final SimpleGrantedAuthority grantedAuthority = new SimpleGrantedAuthority(userRole.name());

        return new User(authenticatedUsername, authenticatedPassword, Collections.singletonList(grantedAuthority));
    }
}
