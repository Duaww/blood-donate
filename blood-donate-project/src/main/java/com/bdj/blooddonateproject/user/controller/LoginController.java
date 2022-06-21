package com.bdj.blooddonateproject.user.controller;

import com.bdj.blooddonateproject.config.UserDetailServiceImp;
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
    private UserDetailServiceImp detailServiceImp;

    @Autowired
    private TokenService tokenService;

    @Autowired
    private JwtUtil jwtUtil;

    @Autowired
    private AuthenticationManager authenticationManager;

    public LoginController(UserService userService, UserDetailServiceImp detailServiceImp, TokenService tokenService,
            JwtUtil jwtUtil, AuthenticationManager authenticationManager) {
        this.userService = userService;
        this.detailServiceImp = detailServiceImp;
        this.tokenService = tokenService;
        this.jwtUtil = jwtUtil;
        this.authenticationManager = authenticationManager;
    }

    @PostMapping("")
    public ResponseEntity<?> login(@RequestBody LoginDTO login) {
        User user = userService.findByUsername(login.getUsername());
        if (user == null || !new BCryptPasswordEncoder().matches(login.getPassword(), user.getPassword())) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("user name or password incorrect");
        }
        // userService.createAdmin();
        UserPrincipal userPrincipal = detailServiceImp.loadUserByUsername(login.getUsername());
        Token token = new Token();
        token.setToken(jwtUtil.generateToken(userPrincipal));
        token.setTokenExpDate(jwtUtil.generateExpirationDate());
        token.setCreatorId(userPrincipal.getId());
        tokenService.createToken(token);
        return ResponseEntity.ok(token.getToken());
    }

    @PostMapping("/logout")
    public ResponseEntity<?> logout(@RequestBody String token) {
        int countRow = tokenService.deleteToken(token);
        if (countRow == 1) {
            SecurityContextHolder.getContext().setAuthentication(null);
            return ResponseEntity.ok("logout success");
        }
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("login failed");

    }

}
