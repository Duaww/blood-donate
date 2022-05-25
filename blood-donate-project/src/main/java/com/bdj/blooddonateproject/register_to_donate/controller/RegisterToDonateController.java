package com.bdj.blooddonateproject.register_to_donate.controller;

import com.bdj.blooddonateproject.register_to_donate.dto.RegisterToDonateDTO;
import com.bdj.blooddonateproject.register_to_donate.service.RegisterToDonateService;

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
@RequestMapping("/api/register-post")
public class RegisterToDonateController {

    @Autowired
    private RegisterToDonateService registerToDonateService;

    public RegisterToDonateController(RegisterToDonateService registerToDonateService) {
        this.registerToDonateService = registerToDonateService;
    }

    @GetMapping("/{postId}")
    @PreAuthorize("hasRole('ROLE_HOSPITAL')")
    public ResponseEntity<?> getListRegisterDonator(@PathVariable("postId") Long postId, Pageable pageable) {
        Object principal = SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        if (principal instanceof UserDetails) {

            Page<RegisterToDonateDTO> listRegister = registerToDonateService.listDonatorRegisterPost(postId, pageable);
            return new ResponseEntity<Page<RegisterToDonateDTO>>(listRegister, HttpStatus.OK);
        }
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("please login");
    }

}
