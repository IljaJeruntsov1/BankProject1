package com.BankServer.demo.controller;

import com.BankServer.demo.dto.AccountResponse;
import com.BankServer.demo.service.AccountService;
import jakarta.servlet.http.HttpSession;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/accounts")
public class AccountController {

    private final AccountService accountService;

    public AccountController(AccountService accountService) {
        this.accountService = accountService;
    }

    @GetMapping
    public AccountResponse getAccounts(
            HttpSession session) {

        return accountService.getAccounts(session);
    }
}