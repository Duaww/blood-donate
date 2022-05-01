package com.bdj.blooddonateproject.jwt.dto;

import java.util.Collection;
import java.util.stream.Collectors;

import com.bdj.blooddonateproject.config.UserPrincipal;

public class InfoAccountDTO {
    private Long id;
    private String username;
    private String password;
    private Collection<String> authorities;

    public Long getId() {
        return this.id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getUsername() {
        return this.username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return this.password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public Collection<String> getAuthorities() {
        return this.authorities;
    }

    public void setAuthorities(Collection<String> authorities) {
        this.authorities = authorities;
    }

    public InfoAccountDTO() {
    }

    public InfoAccountDTO(UserPrincipal userPrincipal) {
        this.id = userPrincipal.getId();
        this.username = userPrincipal.getUsername();
        this.password = userPrincipal.getPassword();
        this.authorities = userPrincipal.getAuthorities()
                .stream()
                .map(role -> role.getAuthority())
                .collect(Collectors.toList());
    }

    public InfoAccountDTO(Long id, String username, String password, Collection<String> authorities) {
        this.id = id;
        this.username = username;
        this.password = password;
        this.authorities = authorities;
    }

}
