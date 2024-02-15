package br.com.yanvelasco.api.domain.consultas.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import br.com.yanvelasco.api.domain.consultas.dto.ConsultaDTO;
import br.com.yanvelasco.api.domain.consultas.entity.ConsultaEntity;
import br.com.yanvelasco.api.domain.consultas.service.AgendaDeConsultas;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import jakarta.transaction.Transactional;
import jakarta.validation.Valid;

@RestController
@RequestMapping("/consultas")
@SecurityRequirement(name = "jwt_auth")
public class ConsultaController {

        @Autowired
        private AgendaDeConsultas agendaDeConsultas;

        @PostMapping
        @Transactional
        public ResponseEntity<Object> agendar(@RequestBody @Valid ConsultaDTO consultaDTO) {
                try {
                        ConsultaEntity consultaAgendada = agendaDeConsultas.execute(consultaDTO); // Aqui você obtém a
                                                                                            // consulta agendada
                        return ResponseEntity.ok(consultaAgendada); // Aqui você retorna a consulta agendada
                } catch (Exception e) {
                        return ResponseEntity.badRequest().body(e.getMessage());
                }
        }
}
