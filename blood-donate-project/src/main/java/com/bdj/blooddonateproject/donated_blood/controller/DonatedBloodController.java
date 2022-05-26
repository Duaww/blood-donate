package com.bdj.blooddonateproject.donated_blood.controller;

import com.bdj.blooddonateproject.config.UserPrincipal;
import com.bdj.blooddonateproject.donated_blood.dto.DonatedBloodDTO;
import com.bdj.blooddonateproject.donated_blood.service.DonatedBloodService;
import com.bdj.blooddonateproject.hospital.model.Hospital;
import com.bdj.blooddonateproject.hospital.service.HospitalService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/donated")
public class DonatedBloodController {

    @Autowired
    private DonatedBloodService donatedBloodService;

    @Autowired
    private HospitalService hospitalService;

    public DonatedBloodController(DonatedBloodService donatedBloodService, HospitalService hospitalService) {
        this.donatedBloodService = donatedBloodService;
        this.hospitalService = hospitalService;
    }

    @GetMapping("")
    @PreAuthorize("hasRole('ROLE_HOSPITAL')")
    public ResponseEntity<?> getListDonatorInSelfHospital(Pageable pageable) {

        Object principal = SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        if (principal instanceof UserDetails) {
            UserPrincipal userPrincipal = (UserPrincipal) principal;
            Hospital hospital = hospitalService.findInfoHospital(userPrincipal.getUsername());
            Page<DonatedBloodDTO> listDonator = donatedBloodService.getListDonatorByHospitalId(hospital.getId(),
                    pageable);

            return new ResponseEntity<Page<DonatedBloodDTO>>(listDonator, HttpStatus.OK);
        }
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("please login");

    }

}