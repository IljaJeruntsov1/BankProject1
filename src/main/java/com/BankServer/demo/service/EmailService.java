package com.BankServer.demo.service;

import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;

@Service
public class EmailService {

    private final JavaMailSender mailSender;

    public EmailService(JavaMailSender mailSender) {
        this.mailSender = mailSender;
    }

    public void sendPasswordEmail(
            String email,
            String firstName,
            String password) {

        SimpleMailMessage message = new SimpleMailMessage();

        message.setTo(email);
        message.setSubject("Your account has been created");

        message.setText(
                "Hello " + firstName + ",\n\n" +
                        "Your account has been created successfully.\n\n" +
                        "Your temporary password is: " + password + "\n\n" +
                        "Please change your password after logging in."
        );

        mailSender.send(message);
    }
}