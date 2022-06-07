package com.bdj.blooddonateproject.donated_blood.service;

import com.bdj.blooddonateproject.donated_blood.dto.DonatedBloodDTO;
import com.bdj.blooddonateproject.donated_blood.dto.FilterDonatedDTO;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface DonatedBloodService {

    Page<DonatedBloodDTO> getListDonatorByHospitalId(Long hospitalId, Pageable pageable);

    Page<DonatedBloodDTO> getHistoryDonate(Long donatorId, Pageable pageable);

    Page<DonatedBloodDTO> getListDonatedWithFilter(Long id, FilterDonatedDTO donatedDTO, Pageable pageable);

}
