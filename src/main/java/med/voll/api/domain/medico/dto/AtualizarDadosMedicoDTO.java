package med.voll.api.domain.medico.dto;

import java.util.UUID;

import jakarta.validation.constraints.NotNull;
import med.voll.api.domain.endereco.EnderecoDTO;

public record AtualizarDadosMedicoDTO(
        @NotNull UUID id,
        String nome,
        String telefone,
        EnderecoDTO enderecoDTO) {
}
