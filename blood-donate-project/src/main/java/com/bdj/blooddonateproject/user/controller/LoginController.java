package com.bdj.blooddonateproject.user.controller;

import javax.servlet.http.HttpServletRequest;

import com.bdj.blooddonateproject.config.UserPrincipal;
import com.bdj.blooddonateproject.jwt.model.JwtUtil;
import com.bdj.blooddonateproject.jwt.model.Token;
import com.bdj.blooddonateproject.jwt.service.TokenService;
import com.bdj.blooddonateproject.user.dto.LoginDTO;
import com.bdj.blooddonateproject.user.model.User;
import com.bdj.blooddonateproject.user.service.UserService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

// @Controller
@CrossOrigin(origins = "*", maxAge = 3600)
@RestController
@RequestMapping("/api/auth")
public class LoginController {

    @Autowired
    private UserService userService;

    @Autowired
    private TokenService tokenService;

    @Autowired
    private JwtUtil jwtUtil;

    @Autowired
    private AuthenticationManager authenticationManager;

    public LoginController(UserService userService, TokenService tokenService, JwtUtil jwtUtil) {
        this.userService = userService;
        this.tokenService = tokenService;
        this.jwtUtil = jwtUtil;
    }

    @PostMapping("")
    public ResponseEntity<?> login(@RequestBody LoginDTO login) {
        User user = userService.findByUsername(login.getUsername());
        if (user == null || !new BCryptPasswordEncoder().matches(login.getPassword(), user.getPassword())) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("user name or password incorrect");
        }
        UserPrincipal userPrincipal = UserPrincipal.create(user);
        Token token = new Token();
        token.setToken(jwtUtil.generateToken(userPrincipal));
        token.setTokenExpDate(jwtUtil.generateExpirationDate());
        token.setCreatorId(userPrincipal.getId());
        tokenService.createToken(token);
        return ResponseEntity.ok(token.getToken());
    }

    @GetMapping("/all")
    public String allAccess() {
        return "Public Content.";
    }

    @GetMapping("/user")
    @PreAuthorize("hasRole('ROLE_DONATOR') or hasRole('ROLE_ADMIN')")
    public String userAccess() {
        Object principal = SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        if (principal instanceof UserDetails) {
            UserPrincipal userPrincipal = (UserPrincipal) principal;
            System.out.println(userPrincipal.getAuthorities());
        }
        return "User Content.";
    }

    @GetMapping("/mod")
    @PreAuthorize("hasRole('ROLE_HOSPITAL')")
    public String moderatorAccess() {
        return "Moderator Board.";
    }

    @GetMapping("/admin")
    @PreAuthorize("hasRole('ROLE_ADMIN')")
    public String adminAccess() {
        return "Admin Board.";
    }

}
