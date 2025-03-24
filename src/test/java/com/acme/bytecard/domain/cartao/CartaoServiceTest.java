package com.acme.bytecard.domain.cartao;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.ArgumentCaptor;
import org.mockito.InjectMocks;
import org.mockito.junit.jupiter.MockitoExtension;

import java.math.BigDecimal;
import java.time.LocalDate;

import static org.junit.jupiter.api.Assertions.assertEquals;
import org.junit.jupiter.api.BeforeEach;
import org.mockito.Mock;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import java.util.Arrays;

import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;
import static org.junit.jupiter.api.Assertions.assertNotNull;

@ExtendWith(MockitoExtension.class)
class CartaoServiceTest {

    @Mock
    private CartaoRepository cartaoRepository;

    @InjectMocks
    private CartaoService cartaoService;


    @Test
    void findAll() {
        // Entrada
        Pageable pageable = PageRequest.of(0, 10);

        // Saída
        Cartao cartao1 = new Cartao();
        Cartao cartao2 = new Cartao();
        Page<Cartao> page = new PageImpl<>(Arrays.asList(cartao1, cartao2));

        when(cartaoRepository.findAll(pageable)).thenReturn(page);

        Page<Cartao> result = cartaoService.findAll(pageable);
        assertNotNull(result);
        assertEquals(2, result.getTotalElements());
    }

    @Test
    void cadastrar() {
        String cliente = "Cliente Teste";
        var limite = new BigDecimal("1000.00");

        Cartao cartaoCriado = cartaoService.cadastrar(cliente, limite);

        assertNotNull(cartaoCriado);
        assertNotNull(cartaoCriado.getNumero());
        assertNotNull(cartaoCriado.getValidade());
        assertNotNull(cartaoCriado.getCvv());
        assertEquals(cliente, cartaoCriado.getCliente());
        assertEquals(limite, cartaoCriado.getLimite());
        assertEquals(StatusCartao.ATIVO, cartaoCriado.getStatus());

        ArgumentCaptor<Cartao> cartaoCaptor = ArgumentCaptor.forClass(Cartao.class);
        verify(cartaoRepository).save(cartaoCaptor.capture());
        Cartao cartaoEnviadoParaBanco = cartaoCaptor.getValue();

        assertEquals(cartaoCriado.getNumero(), cartaoEnviadoParaBanco.getNumero());
        assertEquals(cartaoCriado.getValidade(), cartaoEnviadoParaBanco.getValidade());
        assertEquals(cartaoCriado.getCvv(), cartaoEnviadoParaBanco.getCvv());
        assertEquals(cartaoCriado.getCliente(), cartaoEnviadoParaBanco.getCliente());
        assertEquals(cartaoCriado.getLimite(), cartaoEnviadoParaBanco.getLimite());
        assertEquals(cartaoCriado.getStatus(), cartaoEnviadoParaBanco.getStatus());
    }

    @Test
    void calcularValidade() {
//        var cartaoService = new CartaoService(null);
        var date = LocalDate.of(2000, 1, 1);
        var validade = cartaoService.calcularValidade(date);
        assertEquals("07/2004", validade);
    }
}