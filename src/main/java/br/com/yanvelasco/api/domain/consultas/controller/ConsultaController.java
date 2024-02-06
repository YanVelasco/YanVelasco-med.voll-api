package br.com.yanvelasco.api.domain.consultas.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import br.com.yanvelasco.api.domain.consultas.dto.ConsultaDTO;
import br.com.yanvelasco.api.domain.consultas.entity.Consulta;
import br.com.yanvelasco.api.domain.consultas.repository.ConsultaRepository;
import br.com.yanvelasco.api.domain.medico.entity.Medico;
import br.com.yanvelasco.api.domain.medico.repository.MedicoRepository;
import br.com.yanvelasco.api.domain.paciente.entity.Paciente;
import br.com.yanvelasco.api.domain.paciente.repository.PacienteRepository;
import br.com.yanvelasco.api.infra.exceptions.UserNotFound;
import jakarta.persistence.EntityNotFoundException;
import jakarta.transaction.Transactional;
import jakarta.validation.Valid;

@RestController
@RequestMapping("/consultas")
public class ConsultaController {

        @Autowired
        private ConsultaRepository consultaRepository;

        @Autowired
        private MedicoRepository medicoRepository;

        @Autowired
        private PacienteRepository pacienteRepository;

        @PostMapping
        @Transactional
        public ResponseEntity<Object> agendar(@RequestBody @Valid ConsultaDTO consultaDTO) {
                System.out.println(consultaDTO);

                Medico medico = medicoRepository.findById(consultaDTO.idMedico())
                                .orElseThrow(
                                                () -> new EntityNotFoundException("Médico não encontrado"));

                Paciente paciente = pacienteRepository.findById(consultaDTO.idPaciente())
                                .orElseThrow(() -> new UserNotFound("Paciente não encontrado"));

                Consulta consulta = Consulta.builder()
                                .medico(medico)
                                .paciente(paciente)
                                .data(consultaDTO.data())
                                .build();

                
                consultaRepository.save(consulta);

                return ResponseEntity.ok(consultaDTO);
        }

}
