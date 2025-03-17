package com.acme.bytecard.domain.paciente;

import jakarta.validation.constraints.NotNull;
import com.acme.bytecard.domain.endereco.DadosEndereco;

public record DadosAtualizacaoPaciente(
        @NotNull
        Long id,
        String nome,
        String telefone,
        DadosEndereco endereco) {
}
