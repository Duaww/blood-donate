package com.bdj.blooddonateproject.user.service;

import com.bdj.blooddonateproject.enums.RoleEnum;
import com.bdj.blooddonateproject.user.dto.SignUpDTO;
import com.bdj.blooddonateproject.user.model.User;
import com.bdj.blooddonateproject.user.repo.UserRepo;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.UUID;

@Service
public class UserServiceImpl implements UserService {

    @Autowired
    private UserRepo userRepo;

    @Autowired
    private PasswordEncoder passwordEncoder;

    public UserServiceImpl(UserRepo userRepo) {
        this.userRepo = userRepo;
    }

    @Override
    public User registerNewUser(SignUpDTO request) {
        // TODO Auto-generated method stub
        if (!request.getPassword().equals(request.getConfirmPassword())) {
            throw new IllegalStateException("Confirm password not match");
        }
        if (userRepo.findByUsername(request.getUsername()).isPresent()) {
            throw new IllegalStateException("name exist");
        }

        User newUser = new User(UUID.randomUUID().toString(), request.getUsername(),
                passwordEncoder.encode(request.getPassword()),
                RoleEnum.ROLE_DONATOR, false);
        return userRepo.saveAndFlush(newUser);
    }

    @Override
    public User saveUser(User user) {
        return userRepo.saveAndFlush(user);
    }

}
