package br.com.yanvelasco.api.domain.consultas.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.yanvelasco.api.domain.consultas.dto.ConsultaDTO;
import br.com.yanvelasco.api.domain.consultas.repository.ConsultaRepository;
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

    public void execute(ConsultaDTO consultaDTO) {

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
            return medicoRepository.getReferenceById(consultaDTO.idMedico());
        }

        if (consultaDTO.especialidade() == null) {
            throw  new EspecialidadeException("Voce deve informar o médico ou a especialidade");
        }

        return medicoRepository.escolherMedicoPorEspecialidade(consultaDTO.especialidade(), consultaDTO.data());

    }
}
