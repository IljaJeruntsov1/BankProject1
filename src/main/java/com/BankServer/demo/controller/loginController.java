package com.BankServer.demo.controller;

import jakarta.servlet.http.HttpSession;
import com.BankServer.demo.dto.LoginRequest;
import com.BankServer.demo.service.loginService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/auth")
public class loginController {

    private final loginService loginService;

    public loginController(loginService loginService) {
        this.loginService = loginService;
    }

    @PostMapping("/login")
    public ResponseEntity<?> login(
            @RequestBody LoginRequest request, HttpSession session) {

        return loginService.login(request ,session);
    }
}