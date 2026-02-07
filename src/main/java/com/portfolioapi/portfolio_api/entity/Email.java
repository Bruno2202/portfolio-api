package com.portfolioapi.portfolio_api.entity;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class Email {
    private String issuerEmail;
    private String issuerName;
    private String message;
}