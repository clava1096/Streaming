package com.clava1096.musicstreaming.services.security;

import com.clava1096.musicstreaming.mappers.UserMapper;
import com.clava1096.musicstreaming.models.User;
import com.clava1096.musicstreaming.models.dto.security.AuthenticatedUserDto;
import com.clava1096.musicstreaming.models.dto.security.RegistrationRequest;
import com.clava1096.musicstreaming.models.dto.security.RegistrationResponse;
import com.clava1096.musicstreaming.models.enumpack.UserRole;
import com.clava1096.musicstreaming.models.repositories.UserRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.context.annotation.Bean;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
@Slf4j
public class UserServiceImpl implements UserService {

    private static final String REGISTRATION_SUCCESSFUL = "registration_successful";

    private final UserRepository userRepository;

    private final UserValidationService userValidationService;

    private final BCryptPasswordEncoder bCryptPasswordEncoder;

    @Override
    public User findByUsername(String username) {
        return userRepository.findByUsername(username);
    }

    @Override
    public RegistrationResponse registration(RegistrationRequest registrationRequest) {
        userValidationService.validateUser(registrationRequest);

        final User user = new User();
        user.setEmail(registrationRequest.getEmail());
        user.setUsername(registrationRequest.getUsername());
        user.setName(registrationRequest.getName());
        user.setPassword(bCryptPasswordEncoder.encode(registrationRequest.getPassword()));
        user.setUserRole(String.valueOf(UserRole.USER));
        log.info("Registering user: {}", user);
        userRepository.save(user);

        return new RegistrationResponse(REGISTRATION_SUCCESSFUL);
    }

    @Override
    public AuthenticatedUserDto findAuthenticatedUserByUsername(String username) {
        User user = userRepository.findByUsername(username);
        return UserMapper.INSTANCE.convertToAuthenticatedUserDto(user);
    }

}
