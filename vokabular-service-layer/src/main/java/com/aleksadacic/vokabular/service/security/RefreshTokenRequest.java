package com.aleksadacic.vokabular.service.security;

import lombok.Data;

@Data
public class RefreshTokenRequest {
    private String token;
}
