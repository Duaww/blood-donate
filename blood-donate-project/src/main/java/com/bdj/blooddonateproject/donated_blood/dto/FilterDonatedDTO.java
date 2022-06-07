package com.bdj.blooddonateproject.donated_blood.dto;

import java.util.List;

import com.bdj.blooddonateproject.enums.GroupBlood;

public class FilterDonatedDTO {
    private String name;
    private List<GroupBlood> blood;

    public String getName() {
        return this.name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public List<GroupBlood> getBlood() {
        return this.blood;
    }

    public void setBlood(List<GroupBlood> blood) {
        this.blood = blood;
    }

    public FilterDonatedDTO() {
    }

    public FilterDonatedDTO(String name, List<GroupBlood> blood) {
        this.name = name;
        this.blood = blood;
    }

}
