package com.bewell.ms.users.dto;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class LoginResponse {

    private String token;
    private String refreshToken;
    private long expiresIn;

}
