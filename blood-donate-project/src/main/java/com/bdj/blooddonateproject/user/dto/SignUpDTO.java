package com.bdj.blooddonateproject.user.dto;

public class SignUpDTO {

    private String username;
    private String password;
    private String confirmPassword;

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

    public String getConfirmPassword() {
        return this.confirmPassword;
    }

    public void setConfirmPassword(String confirmPassword) {
        this.confirmPassword = confirmPassword;
    }

    public SignUpDTO() {
    }

    public SignUpDTO(String username, String password, String confirmPassword) {
        this.username = username;
        this.password = password;
        this.confirmPassword = confirmPassword;
    }

}
