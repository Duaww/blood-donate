package com.bdj.blooddonateproject.donated_blood.dto;

import com.bdj.blooddonateproject.donated_blood.model.DonatedBlood;
import com.bdj.blooddonateproject.hospital.dto.HospitalInfoDTO;
import com.bdj.blooddonateproject.user.dto.DonatorInfoDTO;

public class DonatedBloodDTO {

    private Long id;

    private Integer timeDonated;

    private HospitalInfoDTO hospitalInfoDTO;

    private DonatorInfoDTO donatorInfoDTO;

    public Long getId() {
        return this.id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Integer getTimeDonated() {
        return this.timeDonated;
    }

    public void setTimeDonated(Integer timeDonated) {
        this.timeDonated = timeDonated;
    }

    public HospitalInfoDTO getHospitalInfoDTO() {
        return this.hospitalInfoDTO;
    }

    public void setHospitalInfoDTO(HospitalInfoDTO hospitalInfoDTO) {
        this.hospitalInfoDTO = hospitalInfoDTO;
    }

    public DonatorInfoDTO getDonatorInfoDTO() {
        return this.donatorInfoDTO;
    }

    public void setDonatorInfoDTO(DonatorInfoDTO donatorInfoDTO) {
        this.donatorInfoDTO = donatorInfoDTO;
    }

    public DonatedBloodDTO() {
    }

    public DonatedBloodDTO(DonatedBlood donatedBlood) {
        this.id = donatedBlood.getId();
        this.timeDonated = donatedBlood.getTimeDonated();
        this.hospitalInfoDTO = new HospitalInfoDTO(donatedBlood.getHospital());
        this.donatorInfoDTO = new DonatorInfoDTO(donatedBlood.getDonator());
    }

    public DonatedBloodDTO(Long id, Integer timeDonated, HospitalInfoDTO hospitalInfoDTO,
            DonatorInfoDTO donatorInfoDTO) {
        this.id = id;
        this.timeDonated = timeDonated;
        this.hospitalInfoDTO = hospitalInfoDTO;
        this.donatorInfoDTO = donatorInfoDTO;
    }

}
