package com.acme.bytecard.domain.cartao;

import jakarta.validation.constraints.DecimalMin;
import jakarta.validation.constraints.NotBlank;

import java.math.BigDecimal;

public record DadosCadastroCartao (
    @NotBlank
    String cliente,

    @DecimalMin(value = "0.0", inclusive = true)
    BigDecimal limite) {
}
