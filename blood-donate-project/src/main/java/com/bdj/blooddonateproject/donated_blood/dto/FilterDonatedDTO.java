package com.bdj.blooddonateproject.donated_blood.dto;

import java.util.List;

import com.bdj.blooddonateproject.enums.GroupBlood;

public class FilterDonatedDTO {
    private String name;
    private List<GroupBlood> blood;
    private String idCard;

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

    public String getIdCard() {
        return this.idCard;
    }

    public void setIdCard(String idCard) {
        this.idCard = idCard;
    }

    public FilterDonatedDTO() {
    }

    public FilterDonatedDTO(String name, List<GroupBlood> blood, String idCard) {
        this.name = name;
        this.blood = blood;
        this.idCard = idCard;
    }

}
