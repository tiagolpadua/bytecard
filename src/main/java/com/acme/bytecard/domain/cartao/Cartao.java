package com.acme.bytecard.domain.cartao;

import com.acme.bytecard.domain.compra.Compra;
import jakarta.persistence.*;
import lombok.*;

import java.math.BigDecimal;
import java.util.List;

@Table(name = "cartoes")
@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(of = "id")
public class Cartao {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(length = 16, nullable = false)
    private String numero;

    @Column(length = 100, nullable = false)
    private String cliente;

    @Column(length = 7, nullable = false)
    private String validade;

    @Column(length = 3, nullable = false)
    private String cvv;

    @Column(nullable = false, precision = 10, scale = 2)
    private BigDecimal limite;

    @Column(length = 9, nullable = false)
    @Enumerated(EnumType.STRING)
    private StatusCartao status;

    @OneToMany(mappedBy = "cartao", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<Compra> compras;
}