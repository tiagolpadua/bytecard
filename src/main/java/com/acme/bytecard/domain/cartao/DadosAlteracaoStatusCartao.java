package com.acme.bytecard.domain.cartao;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

public record DadosAlteracaoStatusCartao(
    @NotBlank
    String numeroCartao,

    @NotNull
    StatusCartao novoStatus) {
}
