package com.BankServer.demo.repository;

import com.BankServer.demo.entity.Account;
import com.BankServer.demo.entity.Customer;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface AccountRepository extends JpaRepository<Account, Long> {

    List<Account> findByCustomer(Customer customer);

    List<Account> findByCustomerId(Long id);
}