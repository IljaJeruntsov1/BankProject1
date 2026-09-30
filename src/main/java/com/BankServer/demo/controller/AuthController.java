package com.BankServer.demo.controller;


import com.BankServer.demo.dto.LoginRequest;
import com.BankServer.demo.dto.LoginResponse;
import com.BankServer.demo.dto.RegisterRequest;
import com.BankServer.demo.dto.CustomerRegisterResponse;
import com.BankServer.demo.service.LoginService;
import com.BankServer.demo.service.RegistrationCustomerService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/auth")
public class AuthController {
    private final LoginService loginService;
    private final RegistrationCustomerService registrationCustomerService;


    public AuthController(RegistrationCustomerService registrationCustomerService, LoginService loginService) {
        this.registrationCustomerService = registrationCustomerService;
        this.loginService = loginService;
    }

    @PostMapping("/register")
    public ResponseEntity<CustomerRegisterResponse> register (
            @RequestBody RegisterRequest request
            ){
        CustomerRegisterResponse response = registrationCustomerService.register(request);

        return ResponseEntity.status(HttpStatus.CREATED).body(response);
    }

    @PostMapping("/login")
    public ResponseEntity<String> login(
            @RequestBody LoginRequest request
    ) {
        LoginResponse response = loginService.login(request);

        if("Admin".equals(response.getRole())){
            return ResponseEntity.ok("register.html");
        }

        return ResponseEntity.ok("/bank.html");
    }

}
