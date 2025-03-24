package com.acme.bytecard.domain.compra;

import com.acme.bytecard.domain.cartao.Cartao;
import jakarta.persistence.*;
import lombok.*;

import java.math.BigDecimal;
import java.time.LocalDateTime;

@Entity(name = "compras")
@Getter
@Setter
public class Compra {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private BigDecimal valor;

    @Column(nullable = false)
    private LocalDateTime dataHora;

    @Column(nullable = false, length = 100)
    private String estabelecimento;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false, length = 50)
    private CategoriaCompra categoria;

    @ManyToOne
    @JoinColumn(name = "cartao_id", nullable = false)
    private Cartao cartao;
}
