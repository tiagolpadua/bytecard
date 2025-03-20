package com.acme.bytecard.controller;

import com.acme.bytecard.domain.cartao.DadosCadastroCartao;
import com.acme.bytecard.domain.cartao.DadosListagemCartao;
import com.acme.bytecard.domain.paciente.*;
import com.acme.bytecard.service.CartaoService;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.ResponseEntity;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("cartoes")
@RequiredArgsConstructor
@SecurityRequirement(name = "bearerAuth")
public class CartaoController {
    private final CartaoService cartaoService;

    // localhost:8080/cartoes
    @GetMapping
    public ResponseEntity<Page<DadosListagemCartao>> listar(@PageableDefault(size = 10, sort = {"cliente", "numero"}) Pageable paginacao) {
        return  ResponseEntity.ok(cartaoService.findAll(paginacao).map(DadosListagemCartao::new));
    }

    // localhost:8080/cartoes
    @PostMapping
    public ResponseEntity<DadosListagemCartao> cadastrar(@RequestBody @Valid DadosCadastroCartao dadosCadastroCartao) {
        var novoCartao = cartaoService.cadastrar(dadosCadastroCartao.cliente(), dadosCadastroCartao.limite());
        return ResponseEntity.ok(new DadosListagemCartao(novoCartao));
    }
}
