package com.bdj.blooddonateproject.register_to_donate.service;

import com.bdj.blooddonateproject.donator.repo.DonatorRepo;
import com.bdj.blooddonateproject.post_find_donator.repo.PostFindDonatorRepo;
import com.bdj.blooddonateproject.register_to_donate.dto.RegisterToDonateDTO;
import com.bdj.blooddonateproject.register_to_donate.model.RegisterToDonate;
import com.bdj.blooddonateproject.register_to_donate.repo.RegisterToDonateRepo;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

@Service
public class RegisterToDonateServiceImpl implements RegisterToDonateService {

    @Autowired
    private RegisterToDonateRepo registerToDonateRepo;

    @Autowired
    private DonatorRepo donatorRepo;

    @Autowired
    private PostFindDonatorRepo postFindDonatorRepo;

    public RegisterToDonateServiceImpl(RegisterToDonateRepo registerToDonateRepo, DonatorRepo donatorRepo,
            PostFindDonatorRepo postFindDonatorRepo) {
        this.registerToDonateRepo = registerToDonateRepo;
        this.donatorRepo = donatorRepo;
        this.postFindDonatorRepo = postFindDonatorRepo;
    }

    @Override
    public Page<RegisterToDonateDTO> listDonatorRegisterPost(Long postId, Pageable pageable) {
        // TODO Auto-generated method stub
        return registerToDonateRepo.listDonatorRegisterByPostId(postId, pageable).map(RegisterToDonateDTO::new);
    }

    @Override
    public void registerToDonate(Integer timeRegister, Long postId, Long donatorId) {

        RegisterToDonate registerToDonate = new RegisterToDonate();

        registerToDonate.setTimeRegister(timeRegister);
        registerToDonate.setDonator(donatorRepo.getById(donatorId));
        registerToDonate.setPost(postFindDonatorRepo.getById(postId));
        registerToDonateRepo.save(registerToDonate);
    }

}
