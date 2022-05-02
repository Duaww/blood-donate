package com.bdj.blooddonateproject.donator.service;

import com.bdj.blooddonateproject.donator.model.Donator;

public interface DonatorService {
    Donator findInfoDonator(String username);

}