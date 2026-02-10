package com.portfolioapi.portfolio_api.dto;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;

public record EmailDTO(
        @NotBlank(message = "O e-mail do remetente é obrigatório")
        @Email(message = "O e-mail deve ser válido")
        String issuerEmail,

        @NotBlank(message = "O nome é obrigatório")
        String issuerName,

        @NotBlank(message = "A mensagem não pode estar vazia")
        @Size(min = 10, message = "A mensagem deve ter pelo menos 10 caracteres")
        String message
) {}