package com.BankServer.demo.dto;

import java.math.BigDecimal;
import java.util.List;

public class AccountResponse {

    private String customerName;
    private List<AccountData> accounts;

    public AccountResponse(
            String customerName,
            List<AccountData> accounts) {

        this.customerName = customerName;
        this.accounts = accounts;
    }

    public String getCustomerName() {
        return customerName;
    }

    public List<AccountData> getAccounts() {
        return accounts;
    }


    public static class AccountData {

        private String accountName;
        private String iban;
        private BigDecimal balance;
        private BigDecimal reserved;
        private BigDecimal available;
        private String currency;

        public AccountData(
                String accountName,
                String iban,
                BigDecimal balance,
                BigDecimal reserved,
                BigDecimal available,
                String currency) {

            this.accountName = accountName;
            this.iban = iban;
            this.balance = balance;
            this.reserved = reserved;
            this.available = available;
            this.currency = currency;
        }

        public String getAccountName() {
            return accountName;
        }

        public String getIban() {
            return iban;
        }

        public BigDecimal getBalance() {
            return balance;
        }

        public BigDecimal getReserved() {
            return reserved;
        }

        public BigDecimal getAvailable() {
            return available;
        }

        public String getCurrency() {
            return currency;
        }
    }
}