package com.acme.bytecard.domain.cartao;

import com.acme.bytecard.domain.paciente.Paciente;

import java.math.BigDecimal;

public record DadosListagemCartao(Long id,
                                  String numero,
                                  String cliente,
                                  BigDecimal limite,
                                  String validade,
                                  StatusCartao status) {

    public DadosListagemCartao(Cartao cartao) {
        this(cartao.getId(),
                cartao.getNumero(),
                cartao.getCliente(),
                cartao.getLimite(),
                cartao.getValidade(),
                cartao.getStatus());
    }
}