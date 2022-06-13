package com.bdj.blooddonateproject.admin.dto;

public class LockAccountDTO {

    private Long donatorId;

    public Long getDonatorId() {
        return this.donatorId;
    }

    public void setDonatorId(Long donatorId) {
        this.donatorId = donatorId;
    }

    public LockAccountDTO() {
    }

    public LockAccountDTO(Long donatorId) {
        this.donatorId = donatorId;
    }

}
