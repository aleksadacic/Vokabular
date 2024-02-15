package com.aleksadacic.vokabular.service.controllers.auth;

import lombok.Data;

@Data
public class JwtAuthResponse {
    private String token;
    private String refreshToken;
}
