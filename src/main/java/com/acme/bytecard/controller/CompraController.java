package com.acme.bytecard.controller;

import com.acme.bytecard.domain.compra.*;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.math.BigDecimal;

@RestController
@RequestMapping("/compras")
@RequiredArgsConstructor
public class CompraController {

    private final CompraService compraService;

    @PostMapping
    public ResponseEntity<DadosListagemCompra> cadastrarCompra(@RequestBody CompraRequest compraRequest) {
        Compra compra = compraService.cadastrarCompra(
                compraRequest.getNumeroCartao(),
                compraRequest.getValor(),
                compraRequest.getCategoria(),
                compraRequest.getEstabelecimento()
        );
        return ResponseEntity.ok(new DadosListagemCompra(compra));
    }
}