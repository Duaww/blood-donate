package com.bdj.blooddonateproject.user.dto;

public class CreateHospitalDTO {
    private String username;
    private String password;
    private String hospitalName;
    private String address;

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

    public String getHospitalName() {
        return this.hospitalName;
    }

    public void setHospitalName(String hospitalName) {
        this.hospitalName = hospitalName;
    }

    public String getAddress() {
        return this.address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public CreateHospitalDTO() {
    }

    public CreateHospitalDTO(String username, String password, String hospitalName, String address) {
        this.username = username;
        this.password = password;
        this.hospitalName = hospitalName;
        this.address = address;
    }

}
