package com.acme.bytecard.domain.cartao;

import jakarta.transaction.Transactional;
import jakarta.validation.constraints.DecimalMin;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.security.SecureRandom;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.List;

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
        cartaoRepository.save(cartao);
        return cartao;
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

    @Transactional
    public Cartao alterarStatus(String numeroCartao, StatusCartao statusCartao) {
        var cartaoAtual = cartaoRepository.findByNumero(numeroCartao)
                .orElseThrow(() -> new IllegalArgumentException("Cartão não encontrado"));
        cartaoAtual.setStatus(statusCartao);
        return cartaoRepository.save(cartaoAtual);
    }
}
