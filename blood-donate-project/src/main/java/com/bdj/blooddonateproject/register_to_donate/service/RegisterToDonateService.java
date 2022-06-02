package com.bdj.blooddonateproject.register_to_donate.service;

import com.bdj.blooddonateproject.register_to_donate.dto.RegisterToDonateDTO;
import com.bdj.blooddonateproject.register_to_donate.model.RegisterToDonate;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface RegisterToDonateService {
    Page<RegisterToDonateDTO> listDonatorRegisterPost(Long postId, Pageable pageable);

    void registerToDonate(Integer timeRegister, Long postId, Long donatorId) throws Exception;

    void cancelToDonate(Long postId, Long donatorId);

    RegisterToDonate getRegisterByPostAndDonatorId(Long postId, Long donatorId);
}
