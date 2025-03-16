package com.acme.bytecard.api.domain.cartao;

import com.acme.bytecard.api.domain.endereco.DadosEndereco;
import jakarta.validation.Valid;
import jakarta.validation.constraints.*;

import java.math.BigDecimal;

public record DadosCadastroCartao(
        @NotBlank
        String cliente,

        @DecimalMin(value = "0.0", inclusive = true)
        BigDecimal limite) {
}
