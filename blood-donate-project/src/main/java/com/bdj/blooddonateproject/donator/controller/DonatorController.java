package com.bdj.blooddonateproject.donator.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.bdj.blooddonateproject.config.UserPrincipal;
import com.bdj.blooddonateproject.donator.dto.DonatorDTO;
import com.bdj.blooddonateproject.donator.service.DonatorService;

@RestController
@RequestMapping("/api/donator")
public class DonatorController {

    @Autowired
    private DonatorService donatorService;

    public DonatorController(DonatorService donatorService) {
        this.donatorService = donatorService;
    }

    @GetMapping("")
    @PreAuthorize("hasRole('ROLE_ADMIN')")
    public ResponseEntity<?> getListDonator(Pageable pageable) {
        Object principal = SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        if (principal instanceof UserDetails) {

            Page<DonatorDTO> listDonator = donatorService.getListDonator(pageable);
            return new ResponseEntity<Page<DonatorDTO>>(listDonator, HttpStatus.OK);
        }
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("please login");
    }

}
