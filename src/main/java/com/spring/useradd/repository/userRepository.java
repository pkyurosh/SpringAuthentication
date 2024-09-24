package com.spring.useradd.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.spring.useradd.entity.User;



@Repository
public interface userRepository extends JpaRepository<User, Long>{
    User findByUserName(String userName);
}
