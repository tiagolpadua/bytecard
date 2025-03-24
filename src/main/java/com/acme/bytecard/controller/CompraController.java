package com.acme.bytecard.controller;

import com.acme.bytecard.domain.compra.CompraService;
import com.acme.bytecard.domain.compra.DadosCadastroCompra;
import com.acme.bytecard.domain.compra.DadosListagemCompra;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/compras")
@RequiredArgsConstructor
public class CompraController {
    private final CompraService compraService;

    @PostMapping
    public ResponseEntity<Object> cadastrarCompra(@Valid @RequestBody DadosCadastroCompra dadosCadastroCompra) {
        var compra = compraService.cadastrarCompra(
                dadosCadastroCompra.numeroCartao(),
                dadosCadastroCompra.valor(),
                dadosCadastroCompra.categoria(),
                dadosCadastroCompra.estabelecimento()
        );
        return ResponseEntity.ok(new DadosListagemCompra(compra));
    }
}
