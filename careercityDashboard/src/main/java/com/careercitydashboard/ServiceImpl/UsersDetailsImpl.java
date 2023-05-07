package com.careercitydashboard.ServiceImpl;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.Set;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;

import org.springframework.security.core.userdetails.UserDetails;

import com.careercitydashboard.Model.Role;
import com.careercitydashboard.Model.Users;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class UsersDetailsImpl implements UserDetails {

    private static final long serialVersionUID = 1256711395932122675L;
    private Users users;

    public UsersDetailsImpl(Users users) {

        this.users = users;

    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {

        List<GrantedAuthority> list = new ArrayList<GrantedAuthority>();
        Set<Role> roles = users.getRoles();

        for (Role role : roles) {

            list.add(new SimpleGrantedAuthority("ROLE_" + role.getUSER_ROLE()));

        }

        return list;

    }

    @Override
    public String getPassword() {

        return users.getPASSWORD();

    }

    @Override
    public String getUsername() {

        return users.getUsername();

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

        if (this.users.getUSER_STATUS() == null)
            return false;

        if (!this.users.getUSER_STATUS().equals("active"))
            return false;

        return true;

    }

    // User Details
    public String getFirstname() {

        return users.getFirstname();

    }

    public String getLastname() {

        return users.getLastname();

    }

}
