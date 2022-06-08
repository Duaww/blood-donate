package com.bdj.blooddonateproject.donated_blood.service;

import com.bdj.blooddonateproject.donated_blood.dto.DonatedBloodDTO;
import com.bdj.blooddonateproject.donated_blood.dto.FilterDonatedDTO;
import com.bdj.blooddonateproject.donated_blood.repo.DonatedBloodRepo;
import com.bdj.blooddonateproject.enums.GroupBlood;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

@Service
public class DonatedBloodServiceImpl implements DonatedBloodService {

    @Autowired
    private DonatedBloodRepo donatedBloodRepo;

    public DonatedBloodServiceImpl(DonatedBloodRepo donatedBloodRepo) {
        this.donatedBloodRepo = donatedBloodRepo;
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
        return donatedBloodRepo.getListDonatedWithFilter(id, donatedDTO.getName(), convert, pageable)
                .map(DonatedBloodDTO::new);
    }

}
