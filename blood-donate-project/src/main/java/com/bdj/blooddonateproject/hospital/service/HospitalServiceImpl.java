package com.bdj.blooddonateproject.hospital.service;

import com.bdj.blooddonateproject.hospital.dto.HospitalInfoDTO;
import com.bdj.blooddonateproject.hospital.model.Hospital;
import com.bdj.blooddonateproject.hospital.repo.HospitalRepo;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
public class HospitalServiceImpl implements HospitalService {

    @Autowired
    private HospitalRepo hospitalRepo;

    @Override
    public Hospital findInfoHospital(String username) {
        // TODO Auto-generated method stub
        Hospital hospital = hospitalRepo.findInfoHospital(username).orElseThrow(() -> {
            return new UsernameNotFoundException("User name" + username + "is not found in DB");
        });
        return hospital;
    }

    @Override
    public Page<HospitalInfoDTO> listHospital(Pageable pageable) {
        // TODO Auto-generated method stub
        return hospitalRepo.listHospital(pageable).map(HospitalInfoDTO::new);
    }
}