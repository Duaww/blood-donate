package com.bdj.blooddonateproject.donated_blood.service;

import com.bdj.blooddonateproject.donated_blood.dto.DonatedBloodDTO;
import com.bdj.blooddonateproject.donated_blood.dto.FilterDonatedDTO;
import com.bdj.blooddonateproject.donated_blood.model.DonatedBlood;
import com.bdj.blooddonateproject.donated_blood.repo.DonatedBloodRepo;
import com.bdj.blooddonateproject.donator.model.Donator;
import com.bdj.blooddonateproject.donator.repo.DonatorRepo;
import com.bdj.blooddonateproject.enums.GroupBlood;
import com.bdj.blooddonateproject.hospital.model.Hospital;
import com.bdj.blooddonateproject.register_to_donate.model.RegisterToDonate;
import com.bdj.blooddonateproject.register_to_donate.repo.RegisterToDonateRepo;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

@Service
public class DonatedBloodServiceImpl implements DonatedBloodService {

    @Autowired
    private DonatedBloodRepo donatedBloodRepo;

    @Autowired
    private DonatorRepo donatorRepo;

    @Autowired
    private RegisterToDonateRepo registerToDonateRepo;

    public DonatedBloodServiceImpl(DonatedBloodRepo donatedBloodRepo, DonatorRepo donatorRepo,
            RegisterToDonateRepo registerToDonateRepo) {
        this.donatedBloodRepo = donatedBloodRepo;
        this.donatorRepo = donatorRepo;
        this.registerToDonateRepo = registerToDonateRepo;
    }

    @Override
    public Page<DonatedBloodDTO> getListDonatorByHospitalId(Long hospitalId, Pageable pageable) {
        // TODO Auto-generated method stub
        return donatedBloodRepo.getListDonatorByHospitalId(hospitalId, pageable).map(DonatedBloodDTO::new);
    }

    @Override
    public Page<DonatedBloodDTO> getHistoryDonate(Long donatorId, Pageable pageable) {
        // TODO Auto-generated method stub
        return donatedBloodRepo.getHistoryDonated(donatorId, pageable).map(DonatedBloodDTO::new);
    }

    @Override
    public Page<DonatedBloodDTO> getListDonatedWithFilter(Long id, FilterDonatedDTO donatedDTO, Pageable pageable) {
        // TODO Auto-generated method stub
        List<String> convert = new ArrayList<String>();
        List<GroupBlood> listBlood = donatedDTO.getBlood();
        for (int i = 0; i < listBlood.size(); i++) {
            convert.add(listBlood.get(i).name());
        }
        return donatedBloodRepo
                .getListDonatedWithFilter(id, donatedDTO.getName(), convert, donatedDTO.getIdCard(), pageable)
                .map(DonatedBloodDTO::new);
    }

    @Override
    public void confirmDonated(Hospital hospital, Long donatorId, Long postId) {
        RegisterToDonate registerToDonate = registerToDonateRepo.getRegisterByPostAndDonator(donatorId, postId);
        registerToDonate.setIsDonated(true);
        Donator donator = donatorRepo.getById(donatorId);
        DonatedBlood donatedBlood = new DonatedBlood();
        donatedBlood.setHospital(hospital);
        donatedBlood.setDonator(donator);
        donatedBlood.setTimeDonated((int) (new Date().getTime() / 1000));
        registerToDonateRepo.saveAndFlush(registerToDonate);
        donatedBloodRepo.saveAndFlush(donatedBlood);

    }

}
