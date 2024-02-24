package com.aleksadacic.vokabular.service.auth;

import com.aleksadacic.engine.user.AppUserRole;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Set;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class AppUserDTO {
    private String username;
    private String password;
    private Set<AppUserRole> roles;
}
