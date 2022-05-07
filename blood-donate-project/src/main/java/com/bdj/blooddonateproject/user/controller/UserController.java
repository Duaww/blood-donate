package com.bdj.blooddonateproject.user.controller;

import com.bdj.blooddonateproject.config.UserPrincipal;
import com.bdj.blooddonateproject.donator.model.Donator;
import com.bdj.blooddonateproject.donator.service.DonatorService;
import com.bdj.blooddonateproject.enums.RoleEnum;
import com.bdj.blooddonateproject.hospital.model.Hospital;
import com.bdj.blooddonateproject.hospital.service.HospitalService;
import com.bdj.blooddonateproject.user.dto.DonatorInfoDTO;
import com.bdj.blooddonateproject.user.dto.HospitalInfoDTO;
import com.bdj.blooddonateproject.user.dto.SignUpDTO;
import com.bdj.blooddonateproject.user.service.UserService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/user")
public class UserController {

    @Autowired
    private UserService userService;

    @Autowired
    private DonatorService donatorService;

    @Autowired
    private HospitalService hospitalService;

    public UserController(UserService userService, DonatorService donatorService, HospitalService hospitalService) {
        this.userService = userService;
        this.donatorService = donatorService;
        this.hospitalService = hospitalService;
    }

    @PostMapping("")
    public ResponseEntity<?> signUp(@RequestBody SignUpDTO request) {
        try {
            userService.registerNewUser(request);
        } catch (Exception e) {
            // TODO: handle exception
            return ResponseEntity.badRequest().body("error: " + e.getMessage());
        }
        return ResponseEntity.ok().body("message: " + "add user success");
    }

    @GetMapping("/my-profile")
    public ResponseEntity<?> myProfile() {
        Object principal = SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        if (principal instanceof UserDetails) {
            UserPrincipal userPrincipal = (UserPrincipal) principal;
            if (userPrincipal.getAuthorities().contains(new SimpleGrantedAuthority(RoleEnum.ROLE_DONATOR.toString()))) {
                // this is donator
                Donator donator = donatorService.findInfoDonator(userPrincipal.getUsername());
                DonatorInfoDTO donatorInfoDTO = new DonatorInfoDTO(donator);
                return new ResponseEntity<DonatorInfoDTO>(donatorInfoDTO, HttpStatus.OK);
            } else if (userPrincipal.getAuthorities().contains(
                    new SimpleGrantedAuthority(RoleEnum.ROLE_HOSPITAL.toString()))) {
                // this is hospital
                Hospital hospital = hospitalService.findInfoHospital(userPrincipal.getUsername());
                HospitalInfoDTO hospitalInfoDTO = new HospitalInfoDTO(hospital);
                return new ResponseEntity<HospitalInfoDTO>(hospitalInfoDTO, HttpStatus.OK);
            } else {
                // this is admin
                return new ResponseEntity<UserPrincipal>(userPrincipal, HttpStatus.OK);
            }
        }
        return ResponseEntity.badRequest().body("please login");
    }
}
