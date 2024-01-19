package med.voll.api.domain.medico.dto;

import java.util.UUID;

import med.voll.api.domain.medico.entity.Especialidade;
import med.voll.api.domain.medico.entity.Medico;

public record ListarMedicosDTO(
        UUID id,
        String nome,
        String email,
        String crm,
        Especialidade especialidade) {
        public ListarMedicosDTO(Medico medico) {
        this(medico.getId() ,medico.getNome(), medico.getEmail(), medico.getCrm(), medico.getEspecialidade());
    }
}
