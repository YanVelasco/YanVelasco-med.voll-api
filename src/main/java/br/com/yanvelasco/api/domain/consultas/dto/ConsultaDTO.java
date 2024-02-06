package br.com.yanvelasco.api.domain.consultas.dto;

import java.time.LocalDateTime;
import java.util.UUID;

import jakarta.validation.constraints.Future;
import jakarta.validation.constraints.NotNull;

public record ConsultaDTO(
                UUID idMedico,
                UUID idPaciente,
                @NotNull @Future LocalDateTime data) {
}
