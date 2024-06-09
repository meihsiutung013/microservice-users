package com.bewell.ms.users.controller;

import com.bewell.ms.users.dto.LoginDto;
import com.bewell.ms.users.dto.LoginResponse;
import com.bewell.ms.users.dto.RegisterDto;
import com.bewell.ms.users.entity.User;
import com.bewell.ms.users.service.AuthService;
import com.bewell.ms.users.service.JwtService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/auth")
public class AuthController {

    private final JwtService jwtService;
    private final AuthService authService;


    @PostMapping("/signup")
    public ResponseEntity<User> register(@Valid @RequestBody RegisterDto registerDto) {
        User registeredUser = authService.signup(registerDto);
        return ResponseEntity.ok(registeredUser);
    }

    @PostMapping("/login")
    public ResponseEntity<LoginResponse> authenticate(@Valid @RequestBody LoginDto loginDto) {
        User authenticatedUser = authService.authenticate(loginDto);
        String jwtToken = jwtService.generateToken(authenticatedUser);
        LoginResponse loginResponse = LoginResponse.builder().token(jwtToken).expiresIn(jwtService.getExpirationTime()).build();
        return ResponseEntity.ok(loginResponse);
    }
}
