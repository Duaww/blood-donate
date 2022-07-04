package com.bdj.blooddonateproject.register_to_donate.dto;

import com.bdj.blooddonateproject.post_find_donator.dto.PostFindDonatorDTO;
import com.bdj.blooddonateproject.register_to_donate.model.RegisterToDonate;
import com.bdj.blooddonateproject.user.dto.DonatorInfoDTO;

public class RegisterToDonateDTO {

    private Long id;

    private Integer timeRegister;

    private DonatorInfoDTO donatorInfoDTO;

    private PostFindDonatorDTO postFindDonatorDTO;

    private Boolean isDonated;

    public Boolean getIsDonated() {
        return this.isDonated;
    }

    public void setIsDonated(Boolean isDonated) {
        this.isDonated = isDonated;
    }

    public Long getId() {
        return this.id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Integer getTimeRegister() {
        return this.timeRegister;
    }

    public void setTimeRegister(Integer timeRegister) {
        this.timeRegister = timeRegister;
    }

    public PostFindDonatorDTO getPostFindDonatorDTO() {
        return this.postFindDonatorDTO;
    }

    public void setPostFindDonatorDTO(PostFindDonatorDTO postFindDonatorDTO) {
        this.postFindDonatorDTO = postFindDonatorDTO;
    }

    public DonatorInfoDTO getDonatorInfoDTO() {
        return this.donatorInfoDTO;
    }

    public void setDonatorInfoDTO(DonatorInfoDTO donatorInfoDTO) {
        this.donatorInfoDTO = donatorInfoDTO;
    }

    public RegisterToDonateDTO() {
    }

    public RegisterToDonateDTO(RegisterToDonate registerToDonate) {
        this.id = registerToDonate.getId();
        this.timeRegister = registerToDonate.getTimeRegister();
        this.postFindDonatorDTO = new PostFindDonatorDTO(registerToDonate.getPost());
        this.donatorInfoDTO = new DonatorInfoDTO(registerToDonate.getDonator());
        this.isDonated = registerToDonate.getIsDonated();
    }

    public RegisterToDonateDTO(Long id, Integer timeRegister, PostFindDonatorDTO postFindDonatorDTO,
            DonatorInfoDTO donatorInfoDTO, Boolean isDonated) {
        this.id = id;
        this.timeRegister = timeRegister;
        this.postFindDonatorDTO = postFindDonatorDTO;
        this.donatorInfoDTO = donatorInfoDTO;
        this.isDonated = isDonated;
    }

}
