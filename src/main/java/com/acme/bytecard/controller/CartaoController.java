package com.acme.bytecard.controller;

import com.acme.bytecard.domain.cartao.*;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("cartoes")
@RequiredArgsConstructor
public class CartaoController {
    private final CartaoService cartaoService;

    @GetMapping
    public ResponseEntity<Page<DadosListagemCartao>> listar(@PageableDefault(size = 10, sort = {"cliente", "numero"}) Pageable paginacao) {
        return  ResponseEntity.ok(cartaoService.findAll(paginacao).map(DadosListagemCartao::new));
    }

    @PostMapping
    public ResponseEntity<DadosListagemCartao> cadastrar(@RequestBody @Valid DadosCadastroCartao dadosCadastroCartao) {
        var novoCartao = cartaoService.cadastrar(dadosCadastroCartao.cliente(), dadosCadastroCartao.limite());
        return ResponseEntity.ok(new DadosListagemCartao(novoCartao));
    }

    @PutMapping("status")
    public ResponseEntity<DadosListagemCartao> alterarStatus(@RequestBody @Valid DadosAlteracaoStatusCartao dadosAlteracaoStatusCartao) {
        var cartaoAtualizado = cartaoService.alterarStatus(dadosAlteracaoStatusCartao.numeroCartao(), dadosAlteracaoStatusCartao.novoStatus());
        return ResponseEntity.ok(new DadosListagemCartao(cartaoAtualizado));
    }
}
