package com.savvycom.auth_service.domain.entity;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import com.fasterxml.jackson.annotation.JsonIgnore;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@AllArgsConstructor
public class UserDetailsImpl implements UserDetails {
	
    private static final long serialVersionUID = 1L;
    @Getter
    @Setter
    private Long id;
    private String username;

    @JsonIgnore
    private String password;

    @Getter
    @Setter
    private User user;

    private final Collection<? extends GrantedAuthority> authorities;

    public static UserDetailsImpl build(User user) {
        List<GrantedAuthority> authorities = new ArrayList<>();
        user.getRoles().forEach(r -> {
            authorities.add(new SimpleGrantedAuthority(r.getRoleCode()));
        });
        return new UserDetailsImpl(
                user.getUserId(),
                user.getUsername(),

                user.getPassword(),
                user,
                authorities);
    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return this.authorities;
    }

    @Override
    public String getPassword() {
        return this.password;
    }

    @Override
    public String getUsername() {
        return this.username;
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
        return user.isStatus();
    }
}
