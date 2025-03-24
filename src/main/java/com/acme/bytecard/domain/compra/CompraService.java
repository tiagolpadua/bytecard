package com.acme.bytecard.domain.compra;

import com.acme.bytecard.domain.cartao.Cartao;
import com.acme.bytecard.domain.cartao.CartaoRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.time.LocalDateTime;

@Service
@RequiredArgsConstructor
public class CompraService {
    private final CompraRepository compraRepository;
    private final CartaoRepository cartaoRepository;

    public Compra cadastrarCompra(String numeroCartao, BigDecimal valor, CategoriaCompra categoria, String estabelecimento) {
        // Encontrar o cartão pelo número
        Cartao cartao = cartaoRepository.findByNumero(numeroCartao)
                .orElseThrow(() -> new IllegalArgumentException("Cartão não encontrado"));

        // Criar a nova compra
        Compra compra = new Compra();
        compra.setCartao(cartao);
        compra.setDataHora(LocalDateTime.now());
        compra.setValor(valor);
        compra.setCategoria(categoria);
        compra.setEstabelecimento(estabelecimento);

        return compraRepository.save(compra);
    }
}
