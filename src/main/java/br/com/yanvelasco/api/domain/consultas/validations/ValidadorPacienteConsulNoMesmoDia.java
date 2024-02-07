package br.com.yanvelasco.api.domain.consultas.validations;

import org.springframework.beans.factory.annotation.Autowired;

import br.com.yanvelasco.api.domain.consultas.dto.ConsultaDTO;
import br.com.yanvelasco.api.domain.paciente.repository.PacienteRepository;
import br.com.yanvelasco.api.infra.exceptions.ValidadorPacienteConsultaNoMesmoDia;

public class ValidadorPacienteConsulNoMesmoDia {
    @Autowired
    private PacienteRepository pacienteRepository;

    public void execute(ConsultaDTO consultaDTO){
        var horaNoDia = consultaDTO.data();
        var primeiroHorario = horaNoDia.getHour() < 7;
        var ultimoHorario = horaNoDia.getHour() > 18;
        var paciente = pacienteRepository.existsByPacienteAndDataBetween(consultaDTO.idPaciente(),primeiroHorario, ultimoHorario);

        if (Boolean.TRUE.equals(paciente)) {
            throw new ValidadorPacienteConsultaNoMesmoDia("O paciente já possui uma consulta nsse dia");
        }
    }
}
