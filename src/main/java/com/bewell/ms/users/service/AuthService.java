package com.bewell.ms.users.service;

import com.bewell.ms.users.dto.LoginDto;
import com.bewell.ms.users.entity.User;
import com.bewell.ms.users.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class AuthService {

    private final UserRepository userRepository;

    private final AuthenticationManager authenticationManager;

    public User authenticate(LoginDto input) {
        try {
            authenticationManager.authenticate(
                    new UsernamePasswordAuthenticationToken(
                            input.getEmail(),
                            input.getPassword()
                    )
            );
            return userRepository.findByEmail(input.getEmail()).orElseThrow();
        } catch (BadCredentialsException e) {
            System.out.println("Invalid username or password. " + e.getMessage());
            return null;
        }
    }
}