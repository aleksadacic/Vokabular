package com.aleksadacic.vokabular.service.auth;

import lombok.Data;

@Data
public class JwtAuthResponse {
    private String token;
    private String refreshToken;
}
