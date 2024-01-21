package br.com.yanvelasco.api.domain.paciente.dto;

import java.util.UUID;

import br.com.yanvelasco.api.domain.endereco.EnderecoDTO;
import jakarta.validation.constraints.NotNull;

public record AtualizarPacienteDTO(
        @NotNull UUID id,
        String nome,
        String telefone,
        EnderecoDTO enderecoDTO) {

}
