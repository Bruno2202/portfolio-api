package com.portfolioapi.portfolio_api.scheduler;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

import java.time.LocalTime;

@Component
public class PingServer {
    private final RestTemplate restTemplate = new RestTemplate();

    @Value("${portfolioapi.api.url}")
    private String apiUrl;

    @Scheduled(fixedRate = 300000)
    public void ping() {
        try {
            restTemplate.getForObject(apiUrl + "/ping", String.class);
        } catch (Exception e) {
            System.err.println("Falha ao enviar Ping: " + e.getMessage());
        }
    }
}