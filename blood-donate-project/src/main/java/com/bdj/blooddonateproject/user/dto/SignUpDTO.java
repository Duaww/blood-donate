package com.bdj.blooddonateproject.user.dto;

public class SignUpDTO {

    private String username;
    private String fullname;
    private String idCard;
    private String phone;
    private String password;

    public String getUsername() {
        return this.username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getFullname() {
        return this.fullname;
    }

    public void setFulllname(String fulllname) {
        this.fullname = fulllname;
    }

    public String getidCard() {
        return this.idCard;
    }

    public void setidCard(String idCard) {
        this.idCard = idCard;
    }

    public String getPhone() {
        return this.phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getPassword() {
        return this.password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public SignUpDTO() {
    }

    public SignUpDTO(String username, String fullname, String idCard, String phone, String password) {
        this.username = username;
        this.fullname = fullname;
        this.idCard = idCard;
        this.phone = phone;
        this.password = password;
    }

}
