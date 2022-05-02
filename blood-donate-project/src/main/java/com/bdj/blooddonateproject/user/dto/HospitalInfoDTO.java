package com.bdj.blooddonateproject.user.dto;

import com.bdj.blooddonateproject.hospital.model.Hospital;

public class HospitalInfoDTO {
    private String name;

    private String address;

    public String getName() {
        return this.name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getAddress() {
        return this.address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public HospitalInfoDTO() {
    }

    public HospitalInfoDTO(Hospital hospital) {
        this.name = hospital.getName();
        this.address = hospital.getAddress();
    }

    public HospitalInfoDTO(String name, String address) {
        this.name = name;
        this.address = address;
    }

}