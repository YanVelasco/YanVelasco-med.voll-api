package br.com.yanvelasco.api.domain.consultas.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import br.com.yanvelasco.api.domain.consultas.dto.ConsultaDTO;

import br.com.yanvelasco.api.domain.consultas.service.AgendaDeConsultas;

import jakarta.transaction.Transactional;
import jakarta.validation.Valid;

@RestController
@RequestMapping("/consultas")
public class ConsultaController {

        @Autowired
        private AgendaDeConsultas agendaDeConsultas;

        @PostMapping
        @Transactional
        public ResponseEntity<Object> agendar(@RequestBody @Valid ConsultaDTO consultaDTO) {
                try {
                        agendaDeConsultas.execute(consultaDTO);
                        return ResponseEntity.ok(consultaDTO);
                } catch (Exception e) {
                        return ResponseEntity.badRequest().body(e.getMessage());
                }
        }

}
