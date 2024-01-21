package br.com.yanvelasco.api.domain.paciente.dto;

import java.util.UUID;

import br.com.yanvelasco.api.domain.paciente.entity.Paciente;

public record ListarPacientesDTO(
        UUID id,
        String nome,
        String email,
        String cpf
) {
    public ListarPacientesDTO(Paciente paciente){
        this(paciente.getId(), paciente.getNome(), paciente.getEmail(), paciente.getCpf());
    }
}
