package com.bdj.blooddonateproject.donated_blood.dto;

public class ConfirmDonatedDTO {

    private Long idDonator;

    private Long idPost;

    public Long getIdDonator() {
        return this.idDonator;
    }

    public void setIdDonator(Long idDonator) {
        this.idDonator = idDonator;
    }

    public Long getIdPost() {
        return this.idPost;
    }

    public void setIdPost(Long idPost) {
        this.idPost = idPost;
    }

    public ConfirmDonatedDTO() {
    }

    public ConfirmDonatedDTO(Long idDonator, Long idPost) {
        this.idDonator = idDonator;
        this.idPost = idPost;
    }

}
