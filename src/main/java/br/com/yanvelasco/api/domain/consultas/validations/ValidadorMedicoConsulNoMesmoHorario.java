package br.com.yanvelasco.api.domain.consultas.validations;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import br.com.yanvelasco.api.domain.consultas.dto.ConsultaDTO;
import br.com.yanvelasco.api.domain.consultas.repository.ConsultaRepository;
import br.com.yanvelasco.api.domain.medico.repository.MedicoRepository;
import br.com.yanvelasco.api.infra.exceptions.ValidadorMedicoConsulNoMesmoHorarioException;

@Component
public class ValidadorMedicoConsulNoMesmoHorario implements ValidadorAgendamentoDeConsulta{

    @Autowired
    private ConsultaRepository consultaRepository;

    public void execute(ConsultaDTO consultaDTO){
        var medico = consultaRepository.existsByMedicoIdAndData(consultaDTO.idMedico(), consultaDTO.data());

        if (Boolean.TRUE.equals(medico)) {
            throw new ValidadorMedicoConsulNoMesmoHorarioException("O médico já possui uma consulta agendada neste horário");
        }
    }
}
