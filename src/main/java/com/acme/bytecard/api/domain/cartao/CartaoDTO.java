package com.acme.bytecard.api.domain.cartao;

import java.math.BigDecimal;

public record CartaoDTO(Long id, String numero, String cliente, BigDecimal limite, String validade, StatusCartao status) {
    public CartaoDTO(Cartao cartao) {
        this(cartao.getId(), cartao.getNumero(), cartao.getCliente(), cartao.getLimite(), cartao.getValidade(), cartao.getStatus());
    }
}
