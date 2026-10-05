package com.BankServer.demo.service;

import com.BankServer.demo.dto.AccountResponse;
import com.BankServer.demo.entity.Account;
import com.BankServer.demo.entity.Customer;
import com.BankServer.demo.entity.User;
import com.BankServer.demo.repository.AccountRepository;
import com.BankServer.demo.repository.UserRepository;
import jakarta.servlet.http.HttpSession;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.server.ResponseStatusException;

import java.math.BigDecimal;
import java.util.List;

@Service
public class AccountService {

    private final AccountRepository accountRepository;
    private final UserRepository userRepository;

    public AccountService(
            AccountRepository accountRepository,
            UserRepository userRepository) {

        this.accountRepository = accountRepository;
        this.userRepository = userRepository;
    }

    @Transactional(readOnly = true)
    public AccountResponse getAccounts(HttpSession session) {

        Object userIdObject = session.getAttribute("userId");

        if (userIdObject == null) {
            throw new ResponseStatusException(
                    HttpStatus.UNAUTHORIZED,
                    "User is not logged in"
            );
        }

        Long userId = (Long) userIdObject;

        User user = userRepository.findById(userId)
                .orElseThrow(() -> new ResponseStatusException(
                        HttpStatus.UNAUTHORIZED,
                        "User not found"
                ));

        Customer customer = user.getCustomer();

        if (customer == null) {
            throw new ResponseStatusException(
                    HttpStatus.NOT_FOUND,
                    "Customer not found"
            );
        }

        String customerName =
                customer.getFirstName() + " "
                        + customer.getLastName();

        List<Account> accounts =
                accountRepository.findByCustomerId(
                        customer.getId()
                );

        List<AccountResponse.AccountData> accountData =
                accounts.stream()
                        .map(account -> {

                            BigDecimal available =
                                    account.getBalance()
                                            .subtract(
                                                    account.getReserved()
                                            );

                            return new AccountResponse.AccountData(
                                    account.getAccountName(),
                                    account.getIban(),
                                    account.getBalance(),
                                    account.getReserved(),
                                    available,
                                    account.getCurrency()
                            );
                        })
                        .toList();

        return new AccountResponse(
                customerName,
                accountData
        );
    }
}