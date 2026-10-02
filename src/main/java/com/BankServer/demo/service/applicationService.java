package com.BankServer.demo.service;

import com.BankServer.demo.dto.ApplicationRequest;
import com.BankServer.demo.entity.Application;
import com.BankServer.demo.entity.Customer;
import com.BankServer.demo.entity.User;
import com.BankServer.demo.entity.UserCredential;
import com.BankServer.demo.repository.*;
import jakarta.transaction.Transactional;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.Optional;

@Service
public class applicationService {
    private final CustomerRepository customerRepository;
    private final ApplicationRepository applicationRepository;
    private final UserRepository userRepository;
    private final RoleRepository roleRepository;
    private final UserCredentialRepository userCredentialRepository;
    private final PasswordEncoder passwordEncoder;
    private final EmailService emailService;





    public applicationService(CustomerRepository customerRepository, ApplicationRepository applicationRepository, UserRepository userRepository, RoleRepository roleRepository, UserCredentialRepository userCredentialRepository, PasswordEncoder passwordEncoder, EmailService emailService) {
        this.customerRepository = customerRepository;
        this.applicationRepository = applicationRepository;
        this.userRepository = userRepository;
        this.roleRepository = roleRepository;
        this.userCredentialRepository = userCredentialRepository;
        this.passwordEncoder = passwordEncoder;
        this.emailService = emailService;
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

    @Transactional
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

        User user = new User();


        user.setRole(roleRepository.getById(2L));
        user.setCreatedAt(LocalDateTime.now());
        user.setCustomer(customerRepository.getById(customer.getId()));
        user.setStatus("ACTIVE");
        user.setUpdatedAt(LocalDateTime.now());
        String username =customer.getFirstName()+generateCustomerUsername();
        user.setUsername(username);

        String randomPassword = generateRandomPassword();
        String hashedPassword = passwordEncoder.encode(randomPassword);

        UserCredential credential = new UserCredential();

        credential.setUser(user);
        credential.setPasswordHash(hashedPassword);
        credential.setPasswordChangedAt(null);
        credential.setFailedLoginAttempts(0);
        credential.setLockedUntil(null);
        credential.setCreatedAt(LocalDateTime.now());
        credential.setUpdatedAt(LocalDateTime.now());

        userRepository.save(user);
        userCredentialRepository.save(credential);


        emailService.sendPasswordEmail(
                customer.getEmail(),
                customer.getFirstName(),username,
                randomPassword


        );

        return ResponseEntity
                .status(HttpStatus.OK)
                .body("Ticket successfully approved");
    }

    private String generateCustomerNumber() {
        int number = 10000 + (int) (Math.random() * 90000);
        return "CUS-" + number;
    }

    private int generateCustomerUsername() {
        int number = 10000 + (int) (Math.random() * 90000);
        return  number;
    }

    private String generateRandomPassword() {

        String characters =
                "ABCDEFGHIJKLMNOPQRSTUVWXYZ" +
                        "abcdefghijklmnopqrstuvwxyz" +
                        "0123456789";

        StringBuilder password = new StringBuilder();

        for (int i = 0; i < 10; i++) {
            int index = (int) (Math.random() * characters.length());
            password.append(characters.charAt(index));
        }

        return password.toString();
    }
}
