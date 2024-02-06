package br.com.yanvelasco.api.domain.consultas.dto;

import java.time.LocalDateTime;
import java.util.UUID;

import com.fasterxml.jackson.annotation.JsonFormat;

import br.com.yanvelasco.api.domain.medico.entity.Especialidade;
import jakarta.validation.constraints.Future;
import jakarta.validation.constraints.NotNull;

public record ConsultaDTO(
                UUID idMedico,
                @NotNull UUID idPaciente,
                @NotNull @Future @JsonFormat(pattern = "dd/MM/yyyy HH:mm") LocalDateTime data,
                Especialidade especialidade) {
}
