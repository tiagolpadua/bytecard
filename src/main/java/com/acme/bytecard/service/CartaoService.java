package com.acme.bytecard.service;

import com.acme.bytecard.domain.cartao.Cartao;
import com.acme.bytecard.domain.cartao.CartaoRepository;
import com.acme.bytecard.domain.cartao.DadosCadastroCartao;
import com.acme.bytecard.domain.cartao.StatusCartao;
import jakarta.validation.Valid;
import jakarta.validation.constraints.DecimalMin;
import jakarta.validation.constraints.NotBlank;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigDecimal;
import java.security.SecureRandom;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

@Service
@RequiredArgsConstructor
public class CartaoService {
    private final CartaoRepository cartaoRepository;

    public Page<Cartao> findAll(Pageable paginacao) {
        return cartaoRepository.findAll(paginacao);
    }

    @Transactional
    public Cartao cadastrar(String cliente, BigDecimal limite) {
        var cartao = new Cartao();
        cartao.setNumero(gerarNumeroCartao());
        cartao.setCliente(cliente);
        cartao.setValidade(calcularValidade(LocalDate.now()));
        cartao.setCvv(gerarCvv());
        cartao.setLimite(limite);
        cartao.setStatus(StatusCartao.ATIVO);
        return cartaoRepository.save(cartao);
    }

    private String gerarNumeroCartao() {
        SecureRandom random = new SecureRandom();
        StringBuilder numero = new StringBuilder();
        for (int i = 0; i < 16; i++) {
            numero.append(random.nextInt(10));
        }
        return numero.toString();
    }

    public String calcularValidade(LocalDate data) {
        LocalDate validade = data.plusYears(4).plusMonths(6);
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("MM/yyyy");
        return validade.format(formatter);
    }

    private String gerarCvv() {
        SecureRandom random = new SecureRandom();
        StringBuilder cvv = new StringBuilder();
        for (int i = 0; i < 3; i++) {
            cvv.append(random.nextInt(10));
        }
        return cvv.toString();
    }
}
