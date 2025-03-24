package com.acme.bytecard.domain.compra;

import com.acme.bytecard.domain.cartao.Cartao;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface CompraRepository extends JpaRepository<Compra, Long> {
}
