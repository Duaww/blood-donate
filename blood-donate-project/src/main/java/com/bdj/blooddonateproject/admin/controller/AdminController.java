package com.bdj.blooddonateproject.admin.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.bdj.blooddonateproject.admin.dto.LockAccountDTO;
import com.bdj.blooddonateproject.admin.service.AdminService;

@RestController
@RequestMapping("/api/admin")
public class AdminController {

    @Autowired
    private AdminService adminService;

    public AdminController(AdminService adminService) {
        this.adminService = adminService;
    }

    @PostMapping("")
    @PreAuthorize("hasRole('ROLE_ADMIN')")
    public ResponseEntity<?> getListDonator(@RequestBody LockAccountDTO dto) {
        Object principal = SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        if (principal instanceof UserDetails) {

            try {
                adminService.lockDonatorAccount(dto.getDonatorId());
            } catch (Exception e) {
                // TODO: handle exception
                return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("SERVER ERROR");

            }
            return ResponseEntity.status(HttpStatus.OK).body("Lock account success");
        }
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("please login");
    }
}
