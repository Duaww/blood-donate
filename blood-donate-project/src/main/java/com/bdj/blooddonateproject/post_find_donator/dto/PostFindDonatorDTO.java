package com.bdj.blooddonateproject.post_find_donator.dto;

import com.bdj.blooddonateproject.post_find_donator.model.PostFindDonator;
import com.bdj.blooddonateproject.user.dto.HospitalInfoDTO;

public class PostFindDonatorDTO {

    private Long id;

    private String content;

    private Integer createAt;

    private Integer updateAt;

    private Integer deadlineRegister;

    private HospitalInfoDTO hospitalInfo;

    public Long getId() {
        return this.id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public HospitalInfoDTO getHospitalInfo() {
        return this.hospitalInfo;
    }

    public void setHospitalInfo(HospitalInfoDTO hospitalInfo) {
        this.hospitalInfo = hospitalInfo;
    }

    public String getContent() {
        return this.content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public Integer getCreateAt() {
        return this.createAt;
    }

    public void setCreateAt(Integer createAt) {
        this.createAt = createAt;
    }

    public Integer getUpdateAt() {
        return this.updateAt;
    }

    public void setUpdateAt(Integer updateAt) {
        this.updateAt = updateAt;
    }

    public Integer getDeadlineRegister() {
        return this.deadlineRegister;
    }

    public void setDeadlineRegister(Integer deadlineRegister) {
        this.deadlineRegister = deadlineRegister;
    }

    public PostFindDonatorDTO() {
    }

    public PostFindDonatorDTO(PostFindDonator postFindDonator) {
        this.id = postFindDonator.getId();
        this.content = postFindDonator.getContent();
        this.createAt = postFindDonator.getCreateAt();
        this.updateAt = postFindDonator.getUpdateAt();
        this.deadlineRegister = postFindDonator.getDeadlineRegister();
        this.hospitalInfo = new HospitalInfoDTO(postFindDonator.getHospital());
    }

    public PostFindDonatorDTO(Long id, String content, Integer createAt, Integer updateAt, Integer deadlineRegister,
            HospitalInfoDTO hospital) {
        this.content = content;
        this.createAt = createAt;
        this.updateAt = updateAt;
        this.deadlineRegister = deadlineRegister;
        this.hospitalInfo = hospital;
    }

}
