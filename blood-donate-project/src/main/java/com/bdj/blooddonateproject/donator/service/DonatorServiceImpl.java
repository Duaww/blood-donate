package com.bdj.blooddonateproject.donator.service;

import com.bdj.blooddonateproject.donator.model.Donator;
import com.bdj.blooddonateproject.donator.repo.DonatorRepo;

import org.springframework.beans.factory.annotation.Autowired;
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

}