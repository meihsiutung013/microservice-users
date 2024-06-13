package com.bewell.ms.users.controller;

import com.bewell.ms.users.dto.LoginDto;
import com.bewell.ms.users.dto.LoginResponse;
import com.bewell.ms.users.entity.User;
import com.bewell.ms.users.service.AuthService;
import com.bewell.ms.users.service.JwtService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/sessions")
public class AuthController {

    private final JwtService jwtService;
    private final AuthService authService;

    @PostMapping
    public ResponseEntity<LoginResponse> authenticate(@Valid @RequestBody LoginDto loginDto) {
        User authenticatedUser = authService.authenticate(loginDto);
        if (authenticatedUser == null) {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).build();
        }
        String jwtToken = jwtService.generateToken(authenticatedUser);
        LoginResponse loginResponse = LoginResponse.builder().token(jwtToken).expiresIn(jwtService.getExpirationTime()).build();
        return ResponseEntity.ok(loginResponse);
    }
}
