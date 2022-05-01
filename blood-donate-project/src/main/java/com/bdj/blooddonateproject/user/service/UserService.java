package com.bdj.blooddonateproject.user.service;

import com.bdj.blooddonateproject.user.dto.SignUpDTO;
import com.bdj.blooddonateproject.user.model.User;

public interface UserService {

    User registerNewUser(SignUpDTO request);

    User saveUser(User user);

    User findByUsername(String username);
}
