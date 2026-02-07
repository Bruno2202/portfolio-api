package com.portfolioapi.portfolio_api.controller;

import com.portfolioapi.portfolio_api.entity.Email;
import com.portfolioapi.portfolio_api.service.EmailService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class EmailController {

    @Autowired
    private EmailService emailService;

    @PostMapping(path = "/sendEmail")
    public void sendEmail(@RequestBody Email email) {
        emailService.sendIssuerEmail(email);
        emailService.sendEmail(email);
    }
}
