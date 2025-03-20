package com.acme.bytecard.domain.cartao;

import com.acme.bytecard.domain.usuario.Usuario;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.security.core.userdetails.UserDetails;

public interface CartaoRepository extends JpaRepository<Cartao, Long> {
}
