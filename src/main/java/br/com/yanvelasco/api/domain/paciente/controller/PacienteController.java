package br.com.yanvelasco.api.domain.paciente.controller;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.ResponseEntity;

import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.util.UriComponentsBuilder;

import br.com.yanvelasco.api.domain.endereco.Endereco;
import br.com.yanvelasco.api.domain.endereco.EnderecoDTO;
import br.com.yanvelasco.api.domain.paciente.dto.AtualizarPacienteDTO;
import br.com.yanvelasco.api.domain.paciente.dto.ListarPacientesDTO;
import br.com.yanvelasco.api.domain.paciente.dto.PacienteDTO;
import br.com.yanvelasco.api.domain.paciente.entity.Paciente;
import br.com.yanvelasco.api.domain.paciente.repository.PacienteRepository;
import jakarta.transaction.Transactional;
import jakarta.validation.Valid;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.PathVariable;

@RestController
@RequestMapping("/paciente")
public class PacienteController {

    @Autowired
    private PacienteRepository pacienteRepository;

    @PostMapping
    @Transactional
    public ResponseEntity<PacienteDTO> criarPaciente(@RequestBody @Valid PacienteDTO criarPacienteDTO, UriComponentsBuilder uriComponentsBuilder) {
        EnderecoDTO enderecoDTO = criarPacienteDTO.enderecoDTO();
        Endereco endereco = Endereco.builder()
                .logradouro(enderecoDTO.logradouro())
                .bairro(enderecoDTO.bairro())
                .cep(enderecoDTO.cep())
                .cidade(enderecoDTO.cidade())
                .uf(enderecoDTO.uf())
                .complemento(enderecoDTO.complemento())
                .numero(enderecoDTO.numero())
                .build();

        var criarPaciente = Paciente.builder()
                .nome(criarPacienteDTO.nome())
                .email(criarPacienteDTO.email())
                .telefone(criarPacienteDTO.telefone())
                .cpf(criarPacienteDTO.cpf())
                .endereco(endereco)
                .build();

       pacienteRepository.save(criarPaciente);

       var uri = uriComponentsBuilder.path("/paciente{id}").buildAndExpand(criarPaciente.getId()).toUri();

       return ResponseEntity.created(uri).body(new PacienteDTO(criarPaciente));
    }

    @GetMapping
    public ResponseEntity<Page<ListarPacientesDTO>> listagemPacientes(
        @PageableDefault(size = 10, sort = {"nome"}) Pageable paginacaoPageable) {
        var listarPaciente = pacienteRepository.findAllByAtivoTrue(paginacaoPageable).map(ListarPacientesDTO::new);
        return ResponseEntity.ok(listarPaciente);
    }
    
    @PutMapping
    @Transactional
    public ResponseEntity<PacienteDTO> atualizar(@RequestBody @Valid AtualizarPacienteDTO atualizarPacienteDTO) {
        var paciente = pacienteRepository.getReferenceById(atualizarPacienteDTO.id());
        paciente.atualizarPaciente(atualizarPacienteDTO);
        return ResponseEntity.ok(new PacienteDTO(paciente));
    }

    @DeleteMapping("/{id}")
    @Transactional
    public ResponseEntity<Object> deletarPaciente(@PathVariable UUID id){
        var paciente = pacienteRepository.getReferenceById(id);
        paciente.excluir();
        return ResponseEntity.noContent().build();
    }

}
