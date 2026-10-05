package com.BankServer.demo.service;

import com.BankServer.demo.dto.LoginRequest;
import com.BankServer.demo.entity.User;
import com.BankServer.demo.entity.UserCredential;
import com.BankServer.demo.repository.UserCredentialRepository;
import com.BankServer.demo.repository.UserRepository;
import jakarta.servlet.http.HttpSession;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class loginService {

    private final UserRepository userRepository;
    private final UserCredentialRepository userCredentialRepository;
    private final PasswordEncoder passwordEncoder;

    public loginService(
            UserRepository userRepository,
            UserCredentialRepository userCredentialRepository,
            PasswordEncoder passwordEncoder) {

        this.userRepository = userRepository;
        this.userCredentialRepository = userCredentialRepository;
        this.passwordEncoder = passwordEncoder;
    }

    public ResponseEntity<?> login(LoginRequest request, HttpSession session) {

        User user = userRepository.findByUsername(request.getUsername())
                .orElse(null);

        if (user == null) {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED)
                    .body("Invalid username or password");
        }

        UserCredential credential =
                userCredentialRepository.findByUser(user)
                        .orElse(null);

        if (credential == null) {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED)
                    .body("Invalid username or password");
        }

        boolean passwordCorrect = passwordEncoder.matches(
                request.getPassword(),
                credential.getPasswordHash()
        );

        if (!passwordCorrect) {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED)
                    .body("Invalid username or password");
        }
        session.setAttribute("userId", user.getId());
        return ResponseEntity.ok("Login successful");
    }
}