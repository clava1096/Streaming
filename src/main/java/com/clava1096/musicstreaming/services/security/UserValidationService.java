package com.clava1096.musicstreaming.services.security;

import com.clava1096.musicstreaming.models.dto.security.RegistrationRequest;
import com.clava1096.musicstreaming.models.repositories.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class UserValidationService {

    private static final String EMAIL_ALREADY_EXISTS = "Email already exists";

    private static final String USERNAME_ALREADY_EXISTS = "Username already exists";

    private final UserRepository userRepository;

    public void validateUser(RegistrationRequest registrationRequest ) {
        final String email = registrationRequest.getEmail();
        final String username = registrationRequest.getUsername();

        checkEmail(email);
        checkUsername(username);
    }


    private void checkEmail(String email) {
        final boolean exitsUser = userRepository.existsByEmail(email);

        if(exitsUser) {
            throw new RuntimeException();
        }

    }

    private void checkUsername(String username) {
        final boolean exitsUser = userRepository.existsByUsername(username);
        if(exitsUser) {
            throw new RuntimeException();
        }
    }
}
