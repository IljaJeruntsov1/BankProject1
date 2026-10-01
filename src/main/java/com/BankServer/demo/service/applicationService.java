package com.BankServer.demo.service;

import com.BankServer.demo.dto.ApplicationRequest;
import com.BankServer.demo.entity.Application;
import com.BankServer.demo.repository.ApplicationRepository;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;

@Service
public class applicationService {

    private final ApplicationRepository applicationRepository;


    public applicationService(ApplicationRepository applicationRepository) {
        this.applicationRepository = applicationRepository;
    }

    public ResponseEntity<?> createNewTicket(ApplicationRequest request) {
        Application application = new Application();
        application.setFirstName(request.getFirstName());
        application.setLastName(request.getLastName());
        application.setDateOfBirth(request.getDateOfBirth());
        application.setEmail(request.getEmail());
        application.setPhone(request.getPhone());
        application.setCreatedAt(LocalDateTime.now());
        application.setStatus("NOT APPROVED");

        applicationRepository.save(application);

        return ResponseEntity.status(HttpStatus.CREATED).body("Ticket successfully created");
    }
}
