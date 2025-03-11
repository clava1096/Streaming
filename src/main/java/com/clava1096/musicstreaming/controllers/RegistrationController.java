package com.clava1096.musicstreaming.controllers;

import com.clava1096.musicstreaming.config.jwt.JwtProperties;
import com.clava1096.musicstreaming.models.User;
import com.clava1096.musicstreaming.models.dto.security.RegistrationRequest;
import com.clava1096.musicstreaming.models.dto.security.RegistrationResponse;
import com.clava1096.musicstreaming.services.security.UserService;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@Tag(name = "registration-controller")
@RestController
@RequiredArgsConstructor
@RequestMapping
@Slf4j
public class RegistrationController {

    private final UserService userService;

    @PostMapping("/reg")
    public ResponseEntity<String> register(@RequestBody RegistrationRequest registrationRequest) {
        log.info("registration request: {}", registrationRequest);
        final RegistrationResponse registrationResponse = userService.registration(registrationRequest);
        return ResponseEntity.ok(registrationResponse.toString());
    }

}
