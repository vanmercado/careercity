package com.careercitydashboard.util;

import java.util.Collection;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import com.careercitydashboard.Model.Users;

public class MyUserPrincipal implements UserDetails {

    private Users users;

    public MyUserPrincipal(Users users) {

        this.users = users;

    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {

        return null;

    }

    @Override
    public String getPassword() {

        return null;

    }

    @Override
    public String getUsername() {

        return null;

    }

    @Override
    public boolean isAccountNonExpired() {

        return false;

    }

    @Override
    public boolean isAccountNonLocked() {

        return false;

    }

    @Override
    public boolean isCredentialsNonExpired() {

        return false;

    }

    @Override
    public boolean isEnabled() {

        return false;

    }

}
