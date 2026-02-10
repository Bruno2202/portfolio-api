package com.portfolioapi.portfolio_api.scheduler;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

import java.time.LocalTime;

@Component
public class PingServer {
    private final RestTemplate restTemplate = new RestTemplate();

    @Value("${portfolioapi.api.url:http://localhost:8080}")
    private String apiUrl;

    @Value("${portfolioapi.scheduler.on:false}")
    private boolean isOn;

    @Scheduled(fixedRate = 300000)
    public void ping() {
        if (!isOn) {
            return;
        }

        try {
            restTemplate.getForObject(apiUrl + "/actuator/health", String.class);
            // System.out.println("Ping enviado com sucesso em: " + LocalTime.now());
        } catch (Exception e) {
            System.err.println("Ping falhou: " + e.getMessage());
        }
    }
}