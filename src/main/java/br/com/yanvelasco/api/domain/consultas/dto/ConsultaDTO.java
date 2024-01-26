package br.com.yanvelasco.api.domain.consultas.dto;

import java.time.LocalDateTime;
import java.util.UUID;

import br.com.yanvelasco.api.domain.consultas.entity.Consulta;
import br.com.yanvelasco.api.domain.medico.entity.Medico;
import br.com.yanvelasco.api.domain.paciente.entity.Paciente;
import jakarta.validation.constraints.Future;
import jakarta.validation.constraints.NotNull;

public record ConsultaDTO(
                UUID id,
                Medico idMedico,
                @NotNull Paciente idPaciente,
                @NotNull @Future LocalDateTime data) {
        public ConsultaDTO(Consulta consulta) {
                this(consulta.getId(), consulta.getMedico(), consulta.getPaciente(), consulta.getData());
        }
}
