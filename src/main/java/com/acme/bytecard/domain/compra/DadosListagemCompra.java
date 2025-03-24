package com.acme.bytecard.domain.compra;

import com.acme.bytecard.domain.cartao.DadosListagemCartao;

import java.math.BigDecimal;
import java.time.LocalDateTime;

public record DadosListagemCompra(Long id,
                                  BigDecimal valor,
                                  LocalDateTime dataHora,
                                  String estabelecimento,
                                  CategoriaCompra categoria,
                                  DadosListagemCartao cartao) {

    public DadosListagemCompra(Compra compra) {
        this(compra.getId(),
                compra.getValor(),
                compra.getDataHora(),
                compra.getEstabelecimento(),
                compra.getCategoria(),
                new DadosListagemCartao(compra.getCartao())
        );
    }
}
