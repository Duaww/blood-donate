package com.bdj.blooddonateproject.register_to_donate.service;

import com.bdj.blooddonateproject.register_to_donate.dto.RegisterToDonateDTO;
import com.bdj.blooddonateproject.register_to_donate.repo.RegisterToDonateRepo;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

@Service
public class RegisterToDonateServiceImpl implements RegisterToDonateService {

    @Autowired
    private RegisterToDonateRepo registerToDonateRepo;

    public RegisterToDonateServiceImpl(RegisterToDonateRepo registerToDonateRepo) {
        this.registerToDonateRepo = registerToDonateRepo;
    }

    @Override
    public Page<RegisterToDonateDTO> listDonatorRegisterPost(Long postId, Pageable pageable) {
        // TODO Auto-generated method stub
        return registerToDonateRepo.listDonatorRegisterByPostId(postId, pageable).map(RegisterToDonateDTO::new);
    }

}
