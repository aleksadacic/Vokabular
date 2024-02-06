package com.aleksadacic.vokabular.service;

import com.aleksadacic.engine.user.AppUser;
import com.aleksadacic.vokabular.business.entities.AppManager;
import com.aleksadacic.vokabular.service.security.CustomUserDetailsService;
import com.aleksadacic.vokabular.service.security.LoginRequest;
import com.aleksadacic.vokabular.service.utils.JwtUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

@RestController
@RequestMapping("/rest/auth")
public class UserController {
    @Autowired
    AppManager appManager;
    @Autowired
    CustomUserDetailsService service;
    @Autowired
    AuthenticationManager manager;
    @Autowired
    JwtUtil jwtUtil;

    @ResponseBody
    @PostMapping(value = "/login")
    public ResponseEntity<?> login(@RequestBody LoginRequest loginReq) {
        try {
            appManager.login("", "");
            Authentication authentication =
                    manager.authenticate(new UsernamePasswordAuthenticationToken(loginReq.getUsername(), loginReq.getPassword()));
            String email = authentication.getName();
            AppUser user = new AppUser();
            user.setUsername(email);
            String token = jwtUtil.createToken(user);
            return ResponseEntity.ok(token);
        } catch (BadCredentialsException e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("bad credentials");
        } catch (Exception e) {
            e.printStackTrace();
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("errorResponse");
        }
    }

    @PostMapping(value = "/search", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<?> getUser(@RequestBody Map<String, String> request) {
        UserDetails user = service.loadUserByUsername(request.get("username"));
        return ResponseEntity.ok(user.getUsername());
    }
}
