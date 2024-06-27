package com.bewell.ms.users.controller;

import com.bewell.ms.users.dto.LoginDto;
import com.bewell.ms.users.dto.LoginResponse;
import com.bewell.ms.users.dto.RefreshTokenDto;
import com.bewell.ms.users.entity.RefreshToken;
import com.bewell.ms.users.entity.User;
import com.bewell.ms.users.service.AuthService;
import com.bewell.ms.users.service.JwtService;
import com.bewell.ms.users.service.RefreshTokenService;
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
    private final RefreshTokenService refreshTokenService;

    @PostMapping
    public ResponseEntity<LoginResponse> authenticate(@Valid @RequestBody LoginDto loginDto) {
        User authenticatedUser = authService.authenticate(loginDto);
        if (authenticatedUser == null) {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).build();
        }
        RefreshToken refreshToken = refreshTokenService.createRefreshToken(loginDto.getEmail());
        LoginResponse loginResponse = LoginResponse.builder()
                .token(jwtService.generateToken(authenticatedUser))
                .refreshToken(refreshToken.getToken())
                .expiresIn(jwtService.getExpirationTime())
                .build();
        return ResponseEntity.ok(loginResponse);
    }

    @PostMapping("/new")
    public ResponseEntity<LoginResponse> refreshToken(@Valid @RequestBody RefreshTokenDto refreshTokenDto) {
        return refreshTokenService.findByToken(refreshTokenDto.getToken())
                .map(refreshTokenService::verifyExpiration)
                .map(RefreshToken::getUser)
                .map(user -> {
                    return ResponseEntity.ok(
                                LoginResponse.builder()
                                    .token(jwtService.generateToken(user))
                                    .refreshToken(refreshTokenDto.getToken())
                                    .expiresIn(jwtService.getExpirationTime())
                                    .build()
                    );
                }).orElseThrow(() -> new RuntimeException("Refresh token is not in DB..!!"));
    }
}
