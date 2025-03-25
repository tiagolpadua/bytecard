package com.acme.bytecard.domain.compra;

import com.acme.bytecard.domain.cartao.DadosListagemCartao;
import com.acme.bytecard.infra.Util;

import java.math.BigDecimal;
import java.time.LocalDateTime;

public record DadosListagemCompra(Long id,
                                  BigDecimal valor,
                                  String dataHora,
                                  String estabelecimento,
                                  CategoriaCompra categoria,
                                  DadosListagemCartao cartao) {

    public DadosListagemCompra(Compra compra) {
        this(compra.getId(),
                compra.getValor(),
                Util.format(compra.getDataHora()),
                compra.getEstabelecimento(),
                compra.getCategoria(),
                new DadosListagemCartao(compra.getCartao())
        );
    }
}