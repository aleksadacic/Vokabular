package com.aleksadacic.vokabular.service.controllers.auth;

import com.aleksadacic.vokabular.business.SpringContext;
import com.aleksadacic.vokabular.business.users.AuthManager;
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
    public ResponseEntity<String> register(@RequestBody AppUserDTO dto) {
        manager.register(dto.getUsername(), new BCryptPasswordEncoder().encode(dto.getPassword()));
        return ResponseEntity.ok("Success!");
    }

    @PostMapping("/login")
    public ResponseEntity<JwtAuthResponse> login(@RequestBody AppUserDTO dto) {
        authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(dto.getUsername(),
                dto.getPassword()));
        UserDetails user = userDetailsService.loadUserByUsername(dto.getUsername());
        String jwt = jwtUtil.generateToken(user);
        String refreshToken = jwtUtil.generateRefreshToken(Map.of(), user);

        JwtAuthResponse response = new JwtAuthResponse();
        response.setToken(jwt);
        response.setRefreshToken(refreshToken);
        return ResponseEntity.ok(response);
    }

    @PostMapping("/test")
    public ResponseEntity<String> test() {
        return ResponseEntity.ok(SpringContext.getCurrentUser().getUsername());
    }
}
