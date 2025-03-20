package com.acme.bytecard.domain.cartao;

import jakarta.validation.constraints.DecimalMin;
import jakarta.validation.constraints.NotBlank;

import java.math.BigDecimal;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

public record DadosAlteracaoStatusCartao(
        @NotBlank
        String numeroCartao,

        @NotNull
        StatusCartao novoStatus) {
}
