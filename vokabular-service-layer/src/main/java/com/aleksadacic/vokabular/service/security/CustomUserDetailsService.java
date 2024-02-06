package com.aleksadacic.vokabular.service.security;

import com.aleksadacic.engine.user.AppUser;
import com.aleksadacic.vokabular.business.utils.AppUtils;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class CustomUserDetailsService implements UserDetailsService {
    @Override
    public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
        AppUser user = AppUtils.getCurrentUser();
        user.setUsername(email);
        user.setPassword("aleksa");
        List<String> roles = new ArrayList<>();
        roles.add("USER");
        return org.springframework.security.core.userdetails.User.builder()
                .username(user.getUsername())
                .password(user.getPassword())
                .roles(roles.toArray(new String[0]))
                .build();
    }
}
