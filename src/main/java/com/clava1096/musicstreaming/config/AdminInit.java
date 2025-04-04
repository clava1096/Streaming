package com.clava1096.musicstreaming.config;

import com.clava1096.musicstreaming.models.User;
import com.clava1096.musicstreaming.models.enumpack.UserRole;
import com.clava1096.musicstreaming.models.repositories.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.event.ContextRefreshedEvent;
import org.springframework.context.event.EventListener;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class AdminInit {

    private final UserRepository userRepository;

    @Value("${admin.default.password}")
    private String defaultAdminPassword;

    @Value("${admin.default.username}")
    private String defaultAdminUsername;

    private final BCryptPasswordEncoder bCryptPasswordEncoder;

    @EventListener(ContextRefreshedEvent.class)
    public void initAdmin() {
        if (userRepository.findByUsername(defaultAdminUsername) == null) {
            User user = new User();
            user.setUsername(defaultAdminUsername);
            user.setPassword(bCryptPasswordEncoder.encode(defaultAdminPassword));
            user.setUserRole(UserRole.ADMIN);
            userRepository.save(user);
        }
    }
}
