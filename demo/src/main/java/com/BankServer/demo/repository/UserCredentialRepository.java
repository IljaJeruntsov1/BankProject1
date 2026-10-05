package com.BankServer.demo.repository;

import com.BankServer.demo.entity.UserCredential;
import com.BankServer.demo.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface UserCredentialRepository extends JpaRepository<UserCredential, Long> {

    Optional<UserCredential> findByUserId(Long userId);
    Optional<UserCredential> findByUser(User user);
}