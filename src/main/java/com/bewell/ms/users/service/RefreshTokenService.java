package com.bewell.ms.users.service;

import com.bewell.ms.users.entity.RefreshToken;
import com.bewell.ms.users.entity.User;
import com.bewell.ms.users.repository.RefreshTokenRepository;
import com.bewell.ms.users.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.Optional;
import java.util.UUID;

@Service
public class RefreshTokenService {

    @Autowired
    RefreshTokenRepository refreshTokenRepository;

    @Autowired
    UserRepository userRepository;

    public RefreshToken createRefreshToken(String username){
        Optional<User> user = userRepository.findByEmail(username);
        if (user.isPresent()) {
            RefreshToken refreshToken = RefreshToken.builder()
                    .user(user.get())
                    .token(UUID.randomUUID().toString())
                    .expiration(new Date(System.currentTimeMillis() + (60 * 60 * 1000 * 6))) // 6 hours
                    .build();
            return refreshTokenRepository.save(refreshToken);
        }
        return null;
    }

    public Optional<RefreshToken> findByToken(String token) {
        return refreshTokenRepository.findByToken(token);
    }

    public Optional<RefreshToken> findByUserId(Integer userId) {
        return refreshTokenRepository.findByUserId(userId);
    }

    public void delete(RefreshToken refreshToken) {
        refreshTokenRepository.delete(refreshToken);
    }

    public RefreshToken verifyExpiration(RefreshToken token) {
        if (token.getExpiration().compareTo(new Date()) < 0) {
            refreshTokenRepository.delete(token);
            throw new RuntimeException(token.getToken() + " Refresh token is expired. Please make a new login..!");
        }
        return token;
    }
}
