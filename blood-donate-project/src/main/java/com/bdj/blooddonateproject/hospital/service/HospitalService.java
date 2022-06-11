package com.bdj.blooddonateproject.hospital.service;

import com.bdj.blooddonateproject.hospital.dto.HospitalInfoDTO;
import com.bdj.blooddonateproject.hospital.model.Hospital;
import com.bdj.blooddonateproject.user.dto.CreateHospitalDTO;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface HospitalService {
    Hospital findInfoHospital(String username);

    Page<HospitalInfoDTO> listHospital(Pageable pageable);

    void createNewHospital(CreateHospitalDTO createHospitalDTO);
}