package com.clava1096.musicstreaming.controllers;


//import com.clava1096.musicstreaming.config.jwt.JwtTokenService;
import com.clava1096.musicstreaming.config.jwt.JwtTokenService;
import com.clava1096.musicstreaming.models.dto.security.LoginRequest;
import com.clava1096.musicstreaming.models.dto.security.LoginResponse;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@Tag(name = "login-controller")
@RestController
@RequiredArgsConstructor
@RequestMapping("/login")
public class LoginController {
    private final JwtTokenService jwtTokenService;

    @PostMapping
    public ResponseEntity<LoginResponse>login(@RequestBody LoginRequest loginRequest) {
        final LoginResponse loginResponse = jwtTokenService.getLoginResponse(loginRequest);
        return ResponseEntity.ok(loginResponse);

    }
}
