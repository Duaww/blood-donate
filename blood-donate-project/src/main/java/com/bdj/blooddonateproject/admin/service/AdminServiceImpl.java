package com.bdj.blooddonateproject.admin.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.bdj.blooddonateproject.admin.repo.AdminRepo;
import com.bdj.blooddonateproject.user.dto.CreateHospitalDTO;

@Service
public class AdminServiceImpl implements AdminService {

    @Autowired
    private AdminRepo adminRepo;

    public AdminServiceImpl(AdminRepo adminRepo) {
        this.adminRepo = adminRepo;
    }

}
