package com.acme.bytecard.api.controller;

import com.acme.bytecard.api.domain.cartao.*;
import com.acme.bytecard.api.domain.medico.DadosAtualizacaoMedico;
import com.acme.bytecard.api.domain.medico.DadosDetalhamentoMedico;
import com.acme.bytecard.api.domain.paciente.*;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.ResponseEntity;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("cartoes")
public class CartaoController {

    @Autowired
    private CartaoService cartaoService;

    @PostMapping
    @Transactional
    public Cartao cadastrar(@RequestBody @Valid DadosCadastroCartao dadosCadastroCartao) {
        return cartaoService.cadastrar(dadosCadastroCartao);
    }

    @GetMapping
    public Page<DadosListagemCartao> listar(@PageableDefault(size = 10, sort = {"cliente", "numero"}) Pageable paginacao) {
        return cartaoService.listar(paginacao).map(DadosListagemCartao::new);
    }

    @PutMapping
    @Transactional
    public ResponseEntity status(@RequestBody @Valid AlteracaoStatusCartaoRequest alteracaoStatusCartaoRequest) {
        var cartao = cartaoService.alterarStatus(alteracaoStatusCartaoRequest.getNumeroCartao(), alteracaoStatusCartaoRequest.getNovoStatus());
        return ResponseEntity.ok(cartao);
    }

//    @PutMapping
//    @Transactional
//    public void atualizar(@RequestBody @Valid DadosAtualizacaoPaciente dados) {
//        var paciente = repository.getReferenceById(dados.id());
//        paciente.atualizarInformacoes(dados);
//    }
//
//    @DeleteMapping("/{id}")
//    @Transactional
//    public void excluir(@PathVariable Long id) {
//        var paciente = repository.getReferenceById(id);
//        paciente.excluir();
//    }


}
