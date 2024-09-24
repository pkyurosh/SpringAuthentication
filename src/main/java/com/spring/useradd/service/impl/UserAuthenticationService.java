package com.spring.useradd.service.impl;

import com.spring.useradd.entity.User;

public interface UserAuthenticationService {
    public boolean checkAuthDetails(User user);
    void saveUser(User user);
}
