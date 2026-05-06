package com.example.demo.Configuration;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import java.util.Collection;

public class CustomUserDetails implements UserDetails {

    private Long id;
    private String username;
    private String password;
    private String role;
    private Long societyId;

    public CustomUserDetails(Long id, String username, String password, String role, Long societyId) {
        this.id = id;
        this.username = username;
        this.password = password;
        this.role = role;
        this.societyId = societyId;
    }

    public Long getId() {
        return id;
    }

    public String getRole() {
        return role;
    }

    public Long getSocietyId() {
        return societyId;
    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return java.util.List.of(() -> "ROLE_" + role);
    }

    @Override public String getPassword() { return password; }
    @Override public String getUsername() { return username; }
    @Override public boolean isAccountNonExpired() { return true; }
    @Override public boolean isAccountNonLocked() { return true; }
    @Override public boolean isCredentialsNonExpired() { return true; }
    @Override public boolean isEnabled() { return true; }
}
