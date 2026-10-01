package com.BankServer.demo.service;

import com.BankServer.demo.dto.ApplicationRequest;
import com.BankServer.demo.entity.Application;
import com.BankServer.demo.entity.Customer;
import com.BankServer.demo.repository.ApplicationRepository;
import com.BankServer.demo.repository.CustomerRepository;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.Optional;

@Service
public class applicationService {
    private final CustomerRepository customerRepository;

    private final ApplicationRepository applicationRepository;


    public applicationService(CustomerRepository customerRepository, ApplicationRepository applicationRepository) {
        this.customerRepository = customerRepository;
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

    public ResponseEntity<?> approveApplication(long id) {

        Application application = applicationRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Application not found"));


        application.setStatus("APPROVED");

        applicationRepository.save(application);

        Customer customer = new Customer();

        customer.setFirstName(application.getFirstName());
        customer.setLastName(application.getLastName());
        customer.setDateOfBirth(application.getDateOfBirth());
        customer.setEmail(application.getEmail());
        customer.setPhone(application.getPhone());
        customer.setStatus("ACTIVE");
        customer.setCreatedAt(LocalDateTime.now());
        customer.setUpdatedAt(LocalDateTime.now());
        customer.setCustomerNumber(generateCustomerNumber());

        customerRepository.save(customer);


        return ResponseEntity
                .status(HttpStatus.OK)
                .body("Ticket successfully approved");
    }

    private String generateCustomerNumber() {
        int number = 10000 + (int) (Math.random() * 90000);
        return "CUS-" + number;
    }
}
