package br.com.yanvelasco.api.domain.consultas.validations;

import org.springframework.beans.factory.annotation.Autowired;

import br.com.yanvelasco.api.domain.consultas.dto.ConsultaDTO;
import br.com.yanvelasco.api.domain.medico.repository.MedicoRepository;
import br.com.yanvelasco.api.infra.exceptions.ValidadorMedicoConsulNoMesmoHorarioException;

public class ValidadorMedicoConsulNoMesmoHorario {
    @Autowired
    private MedicoRepository medicoRepository;

    public void execute(ConsultaDTO consultaDTO){
        var medico = medicoRepository.existsByIdAndData(consultaDTO.idMedico(), consultaDTO.data());

        if (Boolean.TRUE.equals(medico)) {
            throw new ValidadorMedicoConsulNoMesmoHorarioException("O médico já possui uma consulta agendada neste horário");
        }
    }
}
