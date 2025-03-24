package com.acme.bytecard.domain.compra;

import com.acme.bytecard.domain.compra.CategoriaCompra;
import lombok.Getter;
import lombok.Setter;

import java.math.BigDecimal;

@Getter
@Setter
public class CompraRequest {
    private String numeroCartao;
    private BigDecimal valor;
    private CategoriaCompra categoria;
    private String estabelecimento;
}