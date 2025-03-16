package com.acme.bytecard.api.domain.cartao;

import java.math.BigDecimal;

public record DadosListagemCartao(Long id, String numero, String cliente, BigDecimal limite, String validade) {
    public DadosListagemCartao(Cartao cartao) {
        this(cartao.getId(), cartao.getNumero(), cartao.getCliente(), cartao.getLimite(), cartao.getValidade());
    }
}
