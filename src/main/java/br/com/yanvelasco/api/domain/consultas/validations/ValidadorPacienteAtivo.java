package br.com.yanvelasco.api.domain.consultas.validations;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import br.com.yanvelasco.api.domain.consultas.dto.ConsultaDTO;
import br.com.yanvelasco.api.domain.paciente.repository.PacienteRepository;
import br.com.yanvelasco.api.infra.exceptions.PacienteDesativadoException;

@Component
public class ValidadorPacienteAtivo implements ValidadorAgendamentoDeConsulta{
    

    @Autowired
    private PacienteRepository pacienteRepository;

    public void execute(ConsultaDTO consultaDTO){
        if (consultaDTO.idPaciente() == null) {
            return;
        }

        var paciente = pacienteRepository.findAtivoById(consultaDTO.idPaciente());

        if (Boolean.FALSE.equals(paciente)) {
            throw new PacienteDesativadoException("Paciente está desativado");
        }
    }

}
