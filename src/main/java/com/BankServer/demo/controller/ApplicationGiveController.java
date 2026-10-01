package com.BankServer.demo.controller;

import com.BankServer.demo.dto.ApplicationRequest;
import com.BankServer.demo.service.applicationService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/application")
public class ApplicationGiveController {

    private final applicationService applicationService;

    public ApplicationGiveController(applicationService applicationService) {
        this.applicationService = applicationService;
    }

    @PostMapping("/create")
    public ResponseEntity<?> createTicket(
            @RequestBody ApplicationRequest request) {

        System.out.println("CREATE APPLICATION ENDPOINT CALLED");

        return applicationService.createNewTicket(request);
    }

    @PostMapping("/approveTicket/{userId}")
    public ResponseEntity<?> updateTicketStatus(
            @PathVariable Long userId) {

        return applicationService.approveApplication(userId);
    }
}