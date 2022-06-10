package com.bdj.blooddonateproject.user.service;

import com.bdj.blooddonateproject.donator.model.Donator;
import com.bdj.blooddonateproject.donator.repo.DonatorRepo;
import com.bdj.blooddonateproject.enums.RoleEnum;
import com.bdj.blooddonateproject.user.dto.SignUpDTO;
import com.bdj.blooddonateproject.user.model.User;
import com.bdj.blooddonateproject.user.repo.UserRepo;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.UUID;

@Service
public class UserServiceImpl implements UserService {

    @Autowired
    private UserRepo userRepo;

    @Autowired
    private DonatorRepo donatorRepo;

    @Autowired
    private PasswordEncoder passwordEncoder;

    public UserServiceImpl(UserRepo userRepo, DonatorRepo donatorRepo, PasswordEncoder passwordEncoder) {
        this.userRepo = userRepo;
        this.donatorRepo = donatorRepo;
        this.passwordEncoder = passwordEncoder;
    }

    @Override
    public User registerNewUser(SignUpDTO request) {
        // TODO Auto-generated method stub
        if (userRepo.findByUsername(request.getUsername()).isPresent()) {
            throw new IllegalStateException("name exist");
        }
        User newUser = new User(request.getUsername(),
                passwordEncoder.encode(request.getPassword()),
                RoleEnum.ROLE_DONATOR);
        newUser.setUuid(UUID.randomUUID().toString());
        newUser.setIsDeleted(false);
        Donator newDonator = new Donator();
        newDonator.setDonator(newUser);
        newDonator.setName(request.getFullname());
        newDonator.setIdCard(request.getidCard());
        newDonator.setPhone(request.getPhone());
        userRepo.saveAndFlush(newUser);
        donatorRepo.saveAndFlush(newDonator);
        return newUser;
    }

    @Override
    public User saveUser(User user) {
        return userRepo.saveAndFlush(user);
    }

    @Override
    public User findByUsername(String username) {
        // TODO Auto-generated method stub
        User user = userRepo.findByUsername(username).orElseThrow(() -> {
            return new UsernameNotFoundException("User name" + username + "is not found in DB");
        });
        return user;
    }

}
