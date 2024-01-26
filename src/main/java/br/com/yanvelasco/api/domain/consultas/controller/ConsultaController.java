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
import jakarta.transaction.Transactional;
import jakarta.validation.Valid;

@RestController
@RequestMapping("/consultas")
public class ConsultaController {

    @Autowired
    private ConsultaRepository consultaRepository;

    @PostMapping
    @Transactional
    public ResponseEntity<Object> agendar(@RequestBody @Valid ConsultaDTO consultaDTO) {
        System.out.println(consultaDTO);

        var consulta = Consulta.builder()
                .id(consultaDTO.id())
                .medico(consultaDTO.idMedico())
                .paciente(consultaDTO.idPaciente())
                .data(consultaDTO.data());

        return ResponseEntity.ok(consulta);
    }

}
