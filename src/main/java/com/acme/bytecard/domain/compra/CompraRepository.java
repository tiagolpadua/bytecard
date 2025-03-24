package com.acme.bytecard.domain.compra;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.math.BigDecimal;
import java.time.LocalDateTime;


public interface CompraRepository extends JpaRepository<Compra, Long> {
    @Query("SELECT SUM(c.valor) FROM compras c WHERE c.cartao.numero = :numeroCartao AND c.dataHora BETWEEN :inicio AND :fim")
    BigDecimal findTotalComprasNoIntervalo(@Param("numeroCartao") String numeroCartao, @Param("inicio") LocalDateTime inicio, @Param("fim") LocalDateTime fim);
}
