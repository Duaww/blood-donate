package com.bdj.blooddonateproject.donated_blood.controller;

import com.bdj.blooddonateproject.config.UserPrincipal;
import com.bdj.blooddonateproject.donated_blood.dto.ConfirmDonatedDTO;
import com.bdj.blooddonateproject.donated_blood.dto.DonatedBloodDTO;
import com.bdj.blooddonateproject.donated_blood.dto.FilterDonatedDTO;
import com.bdj.blooddonateproject.donated_blood.service.DonatedBloodService;
import com.bdj.blooddonateproject.donator.model.Donator;
import com.bdj.blooddonateproject.donator.service.DonatorService;
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
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/donated")
public class DonatedBloodController {

    @Autowired
    private DonatedBloodService donatedBloodService;

    @Autowired
    private HospitalService hospitalService;

    @Autowired
    private DonatorService donatorService;

    public DonatedBloodController(DonatedBloodService donatedBloodService, HospitalService hospitalService,
            DonatorService donatorService) {
        this.donatedBloodService = donatedBloodService;
        this.hospitalService = hospitalService;
        this.donatorService = donatorService;
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

    @PostMapping("")
    @PreAuthorize("hasRole('ROLE_HOSPITAL')")
    public ResponseEntity<?> getListDonatedWithFilter(@RequestBody FilterDonatedDTO donatedDTO, Pageable pageable) {

        Object principal = SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        if (principal instanceof UserDetails) {
            UserPrincipal userPrincipal = (UserPrincipal) principal;
            Hospital hospital = hospitalService.findInfoHospital(userPrincipal.getUsername());
            Page<DonatedBloodDTO> listDonator = donatedBloodService.getListDonatedWithFilter(hospital.getId(),
                    donatedDTO, pageable);

            return new ResponseEntity<Page<DonatedBloodDTO>>(listDonator, HttpStatus.OK);
        }
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("please login");
    }

    @GetMapping("/history")
    @PreAuthorize("hasRole('ROLE_DONATOR')")
    public ResponseEntity<?> getHistoryDonated(Pageable pageable) {

        Object principal = SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        if (principal instanceof UserDetails) {
            UserPrincipal userPrincipal = (UserPrincipal) principal;
            Donator donator = donatorService.findInfoDonator(userPrincipal.getUsername());
            Page<DonatedBloodDTO> history = donatedBloodService.getHistoryDonate(donator.getId(), pageable);

            return new ResponseEntity<Page<DonatedBloodDTO>>(history, HttpStatus.OK);
        }
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("please login");
    }

    @PostMapping("/confirm-donated")
    @PreAuthorize("hasRole('ROLE_HOSPITAL')")
    public ResponseEntity<?> confirmDonated(@RequestBody ConfirmDonatedDTO confirmDonatedDTO) {

        Object principal = SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        if (principal instanceof UserDetails) {

            UserPrincipal userPrincipal = (UserPrincipal) principal;
            Hospital hospital = hospitalService.findInfoHospital(userPrincipal.getUsername());
            try {
                donatedBloodService.confirmDonated(hospital, confirmDonatedDTO.getIdDonator(),
                        confirmDonatedDTO.getIdPost());
            } catch (Exception e) {
                // TODO: handle exception
                return ResponseEntity.badRequest().body("error: " + e.getMessage());
            }
            return ResponseEntity.ok().body("message: " + "UPDATE SUCESS");
        }
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("please login");
    }

}
