package com.nklkarthi.autoconfiguration.example;

import org.springframework.data.jpa.repository.JpaRepository;

public interface MyUserRepository extends JpaRepository<MyUser, String>{
    
}