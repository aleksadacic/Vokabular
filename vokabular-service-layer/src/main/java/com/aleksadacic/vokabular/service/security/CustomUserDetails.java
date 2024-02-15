package com.aleksadacic.vokabular.service.security;

import com.aleksadacic.engine.user.AppUser;
import com.aleksadacic.engine.user.AppUserRole;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

public class CustomUserDetails extends AppUser implements UserDetails {

    private final Collection<? extends GrantedAuthority> authorities;

    public CustomUserDetails(AppUser byUsername) {
        setUsername(byUsername.getUsername());
        setPassword(byUsername.getPassword());
        List<GrantedAuthority> auths = new ArrayList<>();

        for (AppUserRole role : byUsername.getRoles()) {
            auths.add(new SimpleGrantedAuthority(role.getName().toUpperCase()));
        }
        this.authorities = auths;
    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return authorities;
    }

    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    @Override
    public boolean isAccountNonLocked() {
        return true;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    @Override
    public boolean isEnabled() {
        return true;
    }
}
