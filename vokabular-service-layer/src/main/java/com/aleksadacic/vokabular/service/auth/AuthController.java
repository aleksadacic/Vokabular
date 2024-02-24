package com.aleksadacic.vokabular.service.auth;

import com.aleksadacic.engine.exceptions.ServiceException;
import com.aleksadacic.engine.framework.service.ServiceUtils;
import com.aleksadacic.vokabular.business.SpringContext;
import com.aleksadacic.vokabular.business.users.AuthManager;
import com.aleksadacic.vokabular.service.security.RefreshTokenRequest;
import com.aleksadacic.vokabular.service.security.UserDetailsServiceImpl;
import com.aleksadacic.vokabular.service.utils.JwtUtil;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Map;

@RestController
@RequestMapping("/api/v1/auth")
@RequiredArgsConstructor
public class AuthController {
    private final AuthManager manager;
    private final AuthenticationManager authenticationManager;
    private final JwtUtil jwtUtil;
    private final UserDetailsServiceImpl userDetailsService;

    @PostMapping("/register")
    public ResponseEntity<?> register(@RequestBody AppUserDTO dto) {
        try {
            manager.register(dto.getUsername(), new BCryptPasswordEncoder().encode(dto.getPassword()));
            return ResponseEntity.ok("Success!");
        } catch (Exception e) {
            return ServiceUtils.errorResponse(e);
        }
    }

    @PostMapping("/login")
    public ResponseEntity<?> login(@RequestBody AppUserDTO dto) {
        try {
            authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(dto.getUsername(),
                    dto.getPassword()));
            UserDetails user = userDetailsService.loadUserByUsername(dto.getUsername());
            String jwt = jwtUtil.generateToken(user);
            String refreshToken = jwtUtil.generateRefreshToken(Map.of(), user);

            JwtAuthResponse response = new JwtAuthResponse();
            response.setToken(jwt);
            response.setRefreshToken(refreshToken);
            return ResponseEntity.ok(response);
        } catch (Exception e) {
            e.printStackTrace();
            return ServiceUtils.errorResponse(e);
        }
    }

    @PostMapping("/refreshToken")
    public ResponseEntity<?> refreshToken(@RequestBody RefreshTokenRequest dto) {
        try {
            String username = jwtUtil.extractUsername(dto.getToken());
            UserDetails user = userDetailsService.loadUserByUsername(username);

            if (!jwtUtil.isTokenValid(dto.getToken(), user)) {
                throw new ServiceException();
            }

            String jwt = jwtUtil.generateToken(user);
            JwtAuthResponse response = new JwtAuthResponse();
            response.setToken(jwt);
            response.setRefreshToken(dto.getToken());
            return ResponseEntity.ok(response);
        } catch (Exception e) {
            return ServiceUtils.errorResponse(e);
        }
    }

    @PostMapping("/test")
    public ResponseEntity<?> test() {
        try {
            return ResponseEntity.ok(SpringContext.getCurrentUser().getUsername());
        } catch (Exception e) {
            return ServiceUtils.errorResponse(e);
        }
    }
}
