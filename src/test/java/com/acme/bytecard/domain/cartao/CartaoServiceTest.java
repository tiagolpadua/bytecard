package com.acme.bytecard.domain.cartao;

import org.junit.jupiter.api.Test;

import java.time.LocalDate;

import static org.junit.jupiter.api.Assertions.*;

class CartaoServiceTest {

//    @Test
//    void findAll() {
//    }
//
//    @Test
//    void cadastrar() {
//    }

    @Test
    void calcularValidade() {
        var cartaoService = new CartaoService(null);
        var date = LocalDate.of(2000, 1, 1);
        var validade = cartaoService.calcularValidade(date);
        assertEquals("07/2004", validade);
    }
}