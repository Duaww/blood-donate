package com.bdj.blooddonateproject.register_to_donate.dto;

public class RegisterToDonateCreateDTO {

    private Long donatorId;

    private Long postId;

    public Long getDonatorId() {
        return this.donatorId;
    }

    public void setDonatorId(Long donatorId) {
        this.donatorId = donatorId;
    }

    public Long getPostId() {
        return this.postId;
    }

    public void setPostId(Long postId) {
        this.postId = postId;
    }

    public RegisterToDonateCreateDTO() {
    }

    public RegisterToDonateCreateDTO(Integer timeRegister, Long donatorId, Long postId) {
        this.donatorId = donatorId;
        this.postId = postId;
    }

}
