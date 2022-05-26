package com.bdj.blooddonateproject.donated_blood.service;

import com.bdj.blooddonateproject.donated_blood.dto.DonatedBloodDTO;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface DonatedBloodService {

    Page<DonatedBloodDTO> getListDonatorByHospitalId(Long hospitalId, Pageable pageable);
}
