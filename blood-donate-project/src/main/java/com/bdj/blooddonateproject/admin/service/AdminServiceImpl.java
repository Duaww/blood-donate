package com.bdj.blooddonateproject.admin.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.bdj.blooddonateproject.admin.repo.AdminRepo;
import com.bdj.blooddonateproject.donator.model.Donator;
import com.bdj.blooddonateproject.donator.repo.DonatorRepo;
import com.bdj.blooddonateproject.user.dto.CreateHospitalDTO;
import com.bdj.blooddonateproject.user.model.User;
import com.bdj.blooddonateproject.user.repo.UserRepo;

@Service
public class AdminServiceImpl implements AdminService {

    @Autowired
    private AdminRepo adminRepo;

    @Autowired
    private DonatorRepo donatorRepo;

    @Autowired
    private UserRepo userRepo;

    public AdminServiceImpl(AdminRepo adminRepo, DonatorRepo donatorRepo, UserRepo userRepo) {
        this.adminRepo = adminRepo;
        this.donatorRepo = donatorRepo;
        this.userRepo = userRepo;
    }

    @Override
    public void lockDonatorAccount(Long donatorId) {
        // TODO Auto-generated method stub
        Donator donator = donatorRepo.findDonatorById(donatorId).orElseThrow(() -> {
            return new UsernameNotFoundException("Donator is not exist");
        });

        User user = donator.getDonator();
        user.setIsDeleted(true);
        userRepo.saveAndFlush(user);
    }

}
