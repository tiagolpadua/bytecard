package com.acme.bytecard.api.domain.cartao;

import lombok.Data;

@Data
public class AlteracaoStatusCartaoRequest {
    private String numeroCartao;
    private StatusCartao novoStatus;
}
