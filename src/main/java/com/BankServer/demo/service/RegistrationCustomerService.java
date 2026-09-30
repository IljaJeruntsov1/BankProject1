package com.BankServer.demo.service;

import com.BankServer.demo.dto.RegisterRequest;
import com.BankServer.demo.dto.CustomerRegisterResponse;
import com.BankServer.demo.entity.Customer;
import com.BankServer.demo.entity.Role;
import com.BankServer.demo.repository.CustomerRepository;
import com.BankServer.demo.repository.RoleRepository;
import com.BankServer.demo.repository.UserCredentialRepository;
import com.BankServer.demo.repository.UserRepository;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.UUID;

@Service
public class RegistrationCustomerService {

    private final CustomerRepository customerRepository;
    private final UserRepository userRepository;
    private final UserCredentialRepository userCredentialRepository;
    private final RoleRepository roleRepository;
    private final PasswordEncoder passwordEncoder;

    public RegistrationCustomerService(
            CustomerRepository customerRepository,
            UserRepository userRepository,
            UserCredentialRepository userCredentialRepository,
            RoleRepository roleRepository,
            PasswordEncoder passwordEncoder
    ) {
        this.customerRepository = customerRepository;
        this.roleRepository = roleRepository;
        this.userRepository = userRepository;
        this.userCredentialRepository = userCredentialRepository;
        this.passwordEncoder = passwordEncoder;
    }

    @Transactional
    public CustomerRegisterResponse register(RegisterRequest request) {

        if (customerRepository.existsByEmail(request.getEmail())) {
            throw new RuntimeException("Email already exists");
        }

        if (customerRepository.existsByPhone(request.getPhone())) {
            throw new RuntimeException("Phone already exists");
        }

        if (userRepository.existsByUsername(request.getUsername())) {
            throw new RuntimeException("Username already exists");
        }


        LocalDateTime now = LocalDateTime.now();

        Customer customer = new Customer();

        customer.setCustomerNumber(generateCustomerNumber());
        customer.setFirstName(request.getFirstName());
        customer.setLastName(request.getLastName());
        customer.setDateOfBirth(request.getDateOfBirth());
        customer.setEmail(request.getEmail());
        customer.setPhone(request.getPhone());
        customer.setStatus("ACTIVE");
        customer.setCreatedAt(now);
        customer.setUpdatedAt(now);

        customer = customerRepository.save(customer);


        System.out.println("Customer ID: " + customer.getId());
        System.out.println("Customer Number: " + customer.getCustomerNumber());

        return new CustomerRegisterResponse(
                customer.getId(),
                customer.getCustomerNumber()
        );

    }

    private String generateCustomerNumber() {
        return "CUST-" +
                UUID.randomUUID()
                        .toString()
                        .replace("-", "")
                        .substring(0, 12)
                        .toUpperCase();
    }


    //        User user = new User();
//
//        user.setCustomer(customer);
//        user.setUsername(request.getUsername());
//        user.setStatus("ACTIVE");
//        user.setRole(clientRole);
//        user.setCreatedAt(now);
//        user.setUpdatedAt(now);
//
//        user = userRepository.save(user);
//
//        UserCredential credential = new UserCredential();
//
//        credential.setUser(user);
//        credential.setPasswordHash(
//                passwordEncoder.encode(request.getPassword())
//        );
//        credential.setPasswordChangedAt(now);
//        credential.setFailedLoginAttempts(0);
//        credential.setLockedUntil(null);
//        credential.setCreatedAt(now);
//        credential.setUpdatedAt(now);
//
//        userCredentialRepository.save(credential);
}