package br.com.yanvelasco.api.domain.consultas.validations;

import java.time.LocalDateTime;
import java.time.LocalTime;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import br.com.yanvelasco.api.domain.consultas.dto.ConsultaDTO;
import br.com.yanvelasco.api.domain.consultas.repository.ConsultaRepository;
import br.com.yanvelasco.api.infra.exceptions.ValidadorPacienteConsultaNoMesmoDia;

@Component
public class ValidadorPacienteConsulNoMesmoDia implements ValidadorAgendamentoDeConsulta{
    @Autowired
    private ConsultaRepository consultaRepository;

    public void execute(ConsultaDTO consultaDTO){
        var horaNoDia = consultaDTO.data();
        var primeiroHorario = LocalDateTime.of(horaNoDia.toLocalDate(), LocalTime.of(7, 0));
        var ultimoHorario = LocalDateTime.of(horaNoDia.toLocalDate(), LocalTime.of(18, 0));
        var paciente = consultaRepository.existsByPacienteIdAndDataBetween(consultaDTO.idPaciente(), primeiroHorario, ultimoHorario);

        if (Boolean.TRUE.equals(paciente)) {
            throw new ValidadorPacienteConsultaNoMesmoDia("O paciente já possui uma consulta nesse dia");
        }
    }
}
