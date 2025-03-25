package com.acme.bytecard.domain.compra;

import com.acme.bytecard.domain.cartao.Cartao;
import com.acme.bytecard.domain.cartao.CartaoRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.LocalDateTime;

@Service
@RequiredArgsConstructor
public class CompraService {
    private final CompraRepository compraRepository;
    private final CartaoRepository cartaoRepository;

    @Transactional
    public Compra cadastrarCompra(String numeroCartao, BigDecimal valorCompra, CategoriaCompra categoria, String estabelecimento) {
        // Encontrar o cartão pelo número
        Cartao cartao = cartaoRepository.findByNumero(numeroCartao)
                .orElseThrow(() -> new IllegalArgumentException("Cartão não encontrado"));

        if (valorCompra.compareTo(cartao.getLimite()) > 0) {
            throw new IllegalArgumentException("Limite do cartão insuficiente");
        }

        var limiteDisponivel = calcularLimiteDisponivel(cartao);
        if (valorCompra.compareTo(limiteDisponivel) > 0) {
            throw new IllegalArgumentException("Limite do cartão insuficiente");
        }

        // Criar a nova compra
        Compra compra = new Compra();
        compra.setCartao(cartao);
        compra.setDataHora(LocalDateTime.now());
        compra.setValor(valorCompra);
        compra.setCategoria(categoria);
        compra.setEstabelecimento(estabelecimento);

        return compraRepository.save(compra);
    }

    public BigDecimal calcularLimiteDisponivel(Cartao cartao) {
        LocalDateTime inicioDoMes = LocalDate.now().withDayOfMonth(1).atStartOfDay();
        LocalDateTime fimDoMes = LocalDate.now().withDayOfMonth(LocalDate.now().lengthOfMonth()).atTime(23, 59, 59);
        BigDecimal totalComprasNoMes = compraRepository.findTotalComprasNoIntervalo(cartao.getNumero(), inicioDoMes, fimDoMes);
        if (totalComprasNoMes == null) {
            totalComprasNoMes = BigDecimal.ZERO;
        }

        return cartao.getLimite().subtract(totalComprasNoMes);
    }

}
