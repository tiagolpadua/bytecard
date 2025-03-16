package com.acme.bytecard.api.domain.medico;

import jakarta.validation.constraints.NotNull;
import com.acme.bytecard.api.domain.endereco.DadosEndereco;

public record DadosAtualizacaoMedico(
        @NotNull
        Long id,
        String nome,
        String telefone,
        DadosEndereco endereco) {
}
