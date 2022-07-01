package com.bdj.blooddonateproject.hospital.service;

import com.bdj.blooddonateproject.enums.RoleEnum;
import com.bdj.blooddonateproject.hospital.dto.HospitalInfoDTO;
import com.bdj.blooddonateproject.hospital.model.Hospital;
import com.bdj.blooddonateproject.hospital.repo.HospitalRepo;
import com.bdj.blooddonateproject.user.dto.CreateHospitalDTO;
import com.bdj.blooddonateproject.user.model.User;
import com.bdj.blooddonateproject.user.repo.UserRepo;

import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class HospitalServiceImpl implements HospitalService {

    @Autowired
    private HospitalRepo hospitalRepo;

    @Autowired
    private UserRepo userRepo;

    @Autowired
    private PasswordEncoder passwordEncoder;

    public HospitalServiceImpl(HospitalRepo hospitalRepo, UserRepo userRepo, PasswordEncoder passwordEncoder) {
        this.hospitalRepo = hospitalRepo;
        this.userRepo = userRepo;
        this.passwordEncoder = passwordEncoder;
    }

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

    @Override
    public void createNewHospital(CreateHospitalDTO createHospitalDTO) {
        // TODO Auto-generated method stub
        if (userRepo.findByUsername(createHospitalDTO.getUsername()).isPresent()) {
            throw new IllegalStateException("name exist");
        }
        User hospital = new User(createHospitalDTO.getUsername(),
                passwordEncoder.encode(createHospitalDTO.getPassword()),
                RoleEnum.ROLE_HOSPITAL);
        hospital.setIsDeleted(false);
        Hospital newHospital = new Hospital();
        newHospital.setHospital(hospital);
        newHospital.setName(createHospitalDTO.getHospitalName());
        newHospital.setAddress(createHospitalDTO.getAddress());
        userRepo.saveAndFlush(hospital);
        hospitalRepo.saveAndFlush(newHospital);

    }
}