package med.voll.api.domain.medico.dto;

import med.voll.api.domain.endereco.EnderecoDTO;
import med.voll.api.domain.medico.entity.Especialidade;

public record CriarMedicoDTO(
        String nome,
        String email,
        String crm,
        Especialidade especialidade,
        EnderecoDTO enderecoDTO
) {
}
