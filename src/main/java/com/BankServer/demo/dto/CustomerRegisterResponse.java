package com.BankServer.demo.dto;

public class CustomerRegisterResponse {

    private Long customerId;
    private String customerNumber;

    public CustomerRegisterResponse() {
    }

    public CustomerRegisterResponse(
            Long customerId,
            String customerNumber

    ) {
        this.customerId = customerId;
        this.customerNumber = customerNumber;

    }



    public Long getCustomerId() {
        return customerId;
    }

    public String getCustomerNumber() {
        return customerNumber;
    }

}