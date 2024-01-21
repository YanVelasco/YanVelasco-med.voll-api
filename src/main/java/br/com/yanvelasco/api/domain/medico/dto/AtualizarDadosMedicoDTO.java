package br.com.yanvelasco.api.domain.medico.dto;

import java.util.UUID;

import br.com.yanvelasco.api.domain.endereco.EnderecoDTO;
import jakarta.validation.constraints.NotNull;

public record AtualizarDadosMedicoDTO(
        @NotNull UUID id,
        String nome,
        String telefone,
        EnderecoDTO enderecoDTO) {
}
