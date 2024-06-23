package com.bewell.ms.users.controller;

import com.bewell.ms.users.dto.RegisterDto;
import com.bewell.ms.users.dto.UserUpdateRequest;
import com.bewell.ms.users.entity.User;
import com.bewell.ms.users.service.UserService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@Validated
@RequestMapping("/api/users")
public class UserController {

    private final UserService userService;

    @PostMapping
    public ResponseEntity<User> register(
            @Valid @RequestBody RegisterDto registerDto
    ) {
        User registeredUser = userService.register(registerDto);
        return ResponseEntity.ok(registeredUser);
    }

    @GetMapping("/{userId}")
    public ResponseEntity<User> getUser(
            @PathVariable Integer userId
    ) {
        HttpStatus httpStatus = validateCurrentUser(userId);
        if (httpStatus.equals(HttpStatus.OK)) {
            User user = userService.getUserById(userId);
            if (user != null) {
                return ResponseEntity.ok(user);
            }
        }
        return ResponseEntity.status(httpStatus).build();
    }

    @PutMapping("/{userId}")
    public ResponseEntity<User> updateUser(
            @PathVariable Integer userId,
            @Valid @RequestBody UserUpdateRequest request
    ) {
        HttpStatus httpStatus = validateCurrentUser(userId);
        if (httpStatus.equals(HttpStatus.OK)) {
            User user = userService.updateUser(userId, request);
            if (user != null) {
                return ResponseEntity.ok(user);
            }
        }
        return ResponseEntity.status(httpStatus).build();
    }

    @DeleteMapping("/{userId}")
    public ResponseEntity<String> deleteUser(
            @PathVariable Integer userId
    ) {
        HttpStatus httpStatus = validateCurrentUser(userId);
        if (httpStatus.equals(HttpStatus.OK)) {
            if (userService.deleteUser(userId)) {
                return ResponseEntity.ok("User deleted.");
            }
        }
        return ResponseEntity.status(httpStatus).build();
    }

    private HttpStatus validateCurrentUser(Integer userId) {
        Boolean isAuthorized = userService.verifyCurrentUser(userId);
        if (isAuthorized != null) {
            if (isAuthorized) {
                return HttpStatus.OK;
            } else {
                return HttpStatus.FORBIDDEN;
            }
        }
        return HttpStatus.NOT_FOUND;
    }
}
