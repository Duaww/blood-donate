package com.bdj.blooddonateproject.donator.service;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import com.bdj.blooddonateproject.donator.dto.DonatorDTO;
import com.bdj.blooddonateproject.donator.model.Donator;

public interface DonatorService {
    Donator findInfoDonator(String username);

    Page<DonatorDTO> getListDonator(Pageable pageable);

}