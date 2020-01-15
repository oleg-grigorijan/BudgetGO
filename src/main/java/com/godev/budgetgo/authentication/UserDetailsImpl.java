package com.godev.budgetgo.authentication;

import com.godev.budgetgo.entity.User;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.Collection;
import java.util.HashSet;

class UserDetailsImpl implements UserDetails {
    private final String username;
    private final String password;
    private final Collection<GrantedAuthority> authorities = new HashSet<>();

    UserDetailsImpl(User entity) {
        username = entity.getLogin();
        password = entity.getPasswordHash();
        authorities.add(new SimpleGrantedAuthority("ROLE_USER"));
        if (entity.isAdmin()) authorities.add(new SimpleGrantedAuthority("ROLE_ADMIN"));
    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return authorities;
    }

    @Override
    public String getPassword() {
        return password;
    }

    @Override
    public String getUsername() {
        return username;
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
