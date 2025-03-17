package com.acme.bytecard.domain.medico;

import jakarta.validation.constraints.NotNull;
import com.acme.bytecard.domain.endereco.DadosEndereco;

public record DadosAtualizacaoMedico(
        @NotNull
        Long id,
        String nome,
        String telefone,
        DadosEndereco endereco) {
}
