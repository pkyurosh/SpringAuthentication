package com.spring.useradd.service.impl;

import com.spring.useradd.entity.User;

public interface UserAuthenticationService {
    void registerUser(User user);

    boolean loginUser(User user);

}
