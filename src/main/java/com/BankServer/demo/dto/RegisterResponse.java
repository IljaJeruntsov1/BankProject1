package com.BankServer.demo.dto;

public class RegisterResponse {

    private Long userId;
    private Long customerId;
    private String customerNumber;
    private String username;
    private String role;
    private String status;

    public RegisterResponse() {
    }

    public RegisterResponse(
            Long userId,
            Long customerId,
            String customerNumber,
            String username,
            String role,
            String status
    ) {
        this.userId = userId;
        this.customerId = customerId;
        this.customerNumber = customerNumber;
        this.username = username;
        this.role = role;
        this.status = status;
    }

    public Long getUserId() {
        return userId;
    }

    public Long getCustomerId() {
        return customerId;
    }

    public String getCustomerNumber() {
        return customerNumber;
    }

    public String getUsername() {
        return username;
    }

    public String getRole() {
        return role;
    }

    public String getStatus() {
        return status;
    }
}