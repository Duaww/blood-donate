package com.bdj.blooddonateproject.user.controller;

import com.bdj.blooddonateproject.user.dto.SignUpDTO;
import com.bdj.blooddonateproject.user.model.User;
import com.bdj.blooddonateproject.user.service.UserService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/test")
public class UserController {

    @Autowired
    private UserService userService;

    public UserController(UserService userService) {
        this.userService = userService;
    }

    @PostMapping("/user")
    public ResponseEntity<?> getUser(@RequestBody SignUpDTO request) {
        User user = userService.findByUsername(request.getUsername());
        return ResponseEntity.ok().body("message: " + user);
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

    @GetMapping("/hello")
    @PreAuthorize("hasRole('ROLE_DONATOR')")
    public ResponseEntity<?> hello() {
        return ResponseEntity.ok("hello");
    }

}
