package com.bdj.blooddonateproject.hospital.dto;

import com.bdj.blooddonateproject.hospital.model.Hospital;

public class HospitalInfoDTO {

    private Long id;
    private String name;

    private String address;

    public Long getId() {
        return this.id;
    }

    public void setId(Long id) {
        this.id = id;
    }

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
        this.id = hospital.getId();
        this.name = hospital.getName();
        this.address = hospital.getAddress();
    }

    public HospitalInfoDTO(Long id, String name, String address) {
        this.id = id;
        this.name = name;
        this.address = address;
    }

}
