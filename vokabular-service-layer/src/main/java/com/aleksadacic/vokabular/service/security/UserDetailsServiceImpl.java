package com.aleksadacic.vokabular.service.security;

import com.aleksadacic.engine.exceptions.TurboException;
import com.aleksadacic.engine.user.AppUser;
import com.aleksadacic.vokabular.business.users.AuthManager;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class UserDetailsServiceImpl implements UserDetailsService {

    private final AuthManager manager;

    @Override
    public UserDetails loadUserByUsername(String username) {
        try {
            AppUser user = manager.getByUsername(username);
            return new CustomUserDetails(user);
        } catch (TurboException e) {
            e.printStackTrace();
            throw new UsernameNotFoundException(username);
        }
    }
}
