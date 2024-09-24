package com.spring.useradd.service.impl;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import com.spring.useradd.entity.User;

import com.spring.useradd.repository.userRepository;

@Service
public class UserAuthentiationServiceImpl implements UserAuthenticationService {
    @Autowired
    private PasswordEncoder passwordEncoder;

    @Autowired
    userRepository userRepo;

    @Override
    public boolean checkAuthDetails(User user) {
        User dbUser = userRepo.findByUserName(user.getUserName());
        if (dbUser == null) {
            return false;
        }

        if (passwordEncoder.matches(user.getPassword(), dbUser.getPassword())) {
            return true;
        }

        return false;
    }

    @Override
    public void saveUser(User user) {
        user.setPassword(passwordEncoder.encode(user.getPassword()));
        userRepo.save(user);
    }
}
