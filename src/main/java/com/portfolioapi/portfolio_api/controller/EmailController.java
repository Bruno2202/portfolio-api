package com.portfolioapi.portfolio_api.controller;

import com.portfolioapi.portfolio_api.dto.EmailDTO;
import com.portfolioapi.portfolio_api.service.EmailService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@Slf4j
@RestController
public class EmailController {

    @Autowired
    private EmailService emailService;

    @PostMapping("/send-email")
    public ResponseEntity<String> sendEmail(@RequestBody EmailDTO emailDto) {
        try {
            emailService.sendEmail(emailDto);

            String response = "E-mail enviado com sucesso!";
            return ResponseEntity.ok(response);
        } catch (Exception e) {
            log.error("Erro ao enviar e-mail para {}: {}", emailDto.issuerEmail(), e.getMessage(), e);

            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body("Falha ao processar o envio: " + e.getMessage());
        }
    }
}
