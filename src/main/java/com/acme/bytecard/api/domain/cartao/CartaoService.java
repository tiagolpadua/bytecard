package com.acme.bytecard.api.domain.cartao;

import jakarta.validation.Valid;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.security.SecureRandom;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.List;

@Service
public class CartaoService {
    private final CartaoRepository cartaoRepository;

    public CartaoService(CartaoRepository cartaoRepository) {
        this.cartaoRepository = cartaoRepository;
    }

    public Cartao cadastrar(@Valid DadosCadastroCartao dadosCadastroCartao) {
        String numero = gerarNumeroCartao();
        String cvv = gerarCvv();
        String validade = calcularValidade();

        Cartao cartao = new Cartao();

        cartao.setNumero(numero);
        cartao.setCliente(dadosCadastroCartao.cliente());
        cartao.setValidade(validade);
        cartao.setCvv(cvv);
        cartao.setLimite(dadosCadastroCartao.limite());
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

    private String gerarCvv() {
        SecureRandom random = new SecureRandom();
        StringBuilder cvv = new StringBuilder();
        for (int i = 0; i < 3; i++) {
            cvv.append(random.nextInt(10));
        }
        return cvv.toString();
    }

    private String calcularValidade() {
        LocalDate validade = LocalDate.now().plusYears(4).plusMonths(6);
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("MM/yyyy");
        return validade.format(formatter);
    }

    public Page<Cartao> listar(Pageable paginacao) {
        return cartaoRepository.findAll(paginacao);
    }
}
