package med.voll.api.domain.medico.dto;

import med.voll.api.domain.medico.entity.Especialidade;
import med.voll.api.domain.medico.entity.Medico;

public record ListarMedicosDTO(
        String nome,
        String email,
        String crm,
        Especialidade especialidade) {
        public ListarMedicosDTO(Medico medico) {
        this(medico.getNome(), medico.getEmail(), medico.getCrm(), medico.getEspecialidade());
    }
}
