package br.com.yanvelasco.api.domain.consultas.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.yanvelasco.api.domain.consultas.dto.ConsultaDTO;
import br.com.yanvelasco.api.domain.consultas.repository.ConsultaRepository;
import br.com.yanvelasco.api.domain.consultas.validations.ValidadorAgendamentoDeConsulta;
import br.com.yanvelasco.api.domain.medico.entity.Medico;
import br.com.yanvelasco.api.domain.medico.repository.MedicoRepository;
import br.com.yanvelasco.api.domain.paciente.repository.PacienteRepository;
import br.com.yanvelasco.api.infra.exceptions.EspecialidadeException;
import br.com.yanvelasco.api.infra.exceptions.UserNotFound;
import br.com.yanvelasco.api.domain.consultas.entity.Consulta;

@Service
public class AgendaDeConsultas {

    @Autowired
    private ConsultaRepository consultaRepository;

    @Autowired
    private MedicoRepository medicoRepository;

    @Autowired
    private PacienteRepository pacienteRepository;

    @Autowired
    private List<ValidadorAgendamentoDeConsulta> validadores;

    public void execute(ConsultaDTO consultaDTO) {

        validadores.forEach(v -> v.execute(consultaDTO));

        var medico = escolherMedico(consultaDTO);

        var paciente = pacienteRepository.findById(consultaDTO.idPaciente())
                .orElseThrow(() -> new UserNotFound("Paciente não encontrado"));

        var consulta = Consulta.builder()
                .medico(medico)
                .paciente(paciente)
                .data(consultaDTO.data())
                .build();

        consultaRepository.save(consulta);

    }

    private Medico escolherMedico(ConsultaDTO consultaDTO) {
        if (consultaDTO.idMedico() != null) {
            return medicoRepository.findById(consultaDTO.idMedico())
                    .orElseThrow(() -> new UserNotFound("Médico não encontrado"));
        } else if (consultaDTO.especialidade() != null) {
            return medicoRepository.escolherMedicoPorEspecialidade(consultaDTO.especialidade(), consultaDTO.data());
        } else {
            throw new EspecialidadeException("Você deve informar o médico ou a especialidade");
        }
    }
}
