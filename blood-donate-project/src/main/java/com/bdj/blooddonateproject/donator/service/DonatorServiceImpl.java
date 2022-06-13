package com.bdj.blooddonateproject.donator.service;

import com.bdj.blooddonateproject.donator.dto.DonatorDTO;
import com.bdj.blooddonateproject.donator.model.Donator;
import com.bdj.blooddonateproject.donator.repo.DonatorRepo;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
public class DonatorServiceImpl implements DonatorService {

    @Autowired
    private DonatorRepo donatorRepo;

    @Override
    public Donator findInfoDonator(String username) {
        // TODO Auto-generated method stub
        Donator donator = donatorRepo.findInfoDonator(username).orElseThrow(() -> {
            return new UsernameNotFoundException("User name" + username + "is not found in DB");
        });
        return donator;
    }

    @Override
    public Page<DonatorDTO> getListDonator(Pageable pageable) {
        // TODO Auto-generated method stub
        Page<Donator> listDonator = donatorRepo.getListDonator(pageable);
        Page<DonatorDTO> listDonatorDTO = listDonator.map(DonatorDTO::new);
        for (int i = 0; i < listDonatorDTO.getContent().size(); i++) {
            int numberDonated = donatorRepo.getNumDonatedOfUser(listDonatorDTO.getContent().get(i).getId());
            listDonatorDTO.getContent().get(i).setNumDonated(numberDonated);
        }
        return listDonatorDTO;
    }

}