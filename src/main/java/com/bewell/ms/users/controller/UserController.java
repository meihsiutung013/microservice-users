package com.bewell.ms.users.controller;

import com.bewell.ms.users.dto.RegisterDto;
import com.bewell.ms.users.entity.User;
import com.bewell.ms.users.service.UserService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/users")
public class UserController {

    private final UserService userService;

    @PostMapping
    public ResponseEntity<User> register(@Valid @RequestBody RegisterDto registerDto) {
        User registeredUser = userService.register(registerDto);
        return ResponseEntity.ok(registeredUser);
    }

    @GetMapping
    public ResponseEntity<User> getUserByEmail(@RequestParam  String email) {
        User user = userService.getUserByEmail(email);
        if (user != null) {
            return ResponseEntity.ok(user);
        } else {
            return ResponseEntity.notFound().build();
        }
    }

}
