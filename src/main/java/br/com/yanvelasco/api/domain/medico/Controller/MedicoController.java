package br.com.yanvelasco.api.domain.medico.Controller;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.util.UriComponentsBuilder;

import br.com.yanvelasco.api.domain.endereco.Endereco;
import br.com.yanvelasco.api.domain.endereco.EnderecoDTO;
import br.com.yanvelasco.api.domain.medico.dto.AtualizarDadosMedicoDTO;
import br.com.yanvelasco.api.domain.medico.dto.ListarMedicosDTO;
import br.com.yanvelasco.api.domain.medico.dto.MedicoDTO;
import br.com.yanvelasco.api.domain.medico.entity.Medico;
import br.com.yanvelasco.api.domain.medico.repository.MedicoRepository;
import jakarta.transaction.Transactional;
import jakarta.validation.Valid;

import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestParam;


@RestController
@RequestMapping("/medico")
public class MedicoController {

    @Autowired
    private MedicoRepository medicoRepository;

    @PostMapping
    @Transactional
    public ResponseEntity<MedicoDTO> cadastrarMedico(@RequestBody @Valid MedicoDTO medicoDTO, UriComponentsBuilder uriComponentsBuilder) {

        EnderecoDTO enderecoDTO = medicoDTO.enderecoDTO();
        Endereco endereco = Endereco.builder()
                .logradouro(enderecoDTO.logradouro())
                .bairro(enderecoDTO.bairro())
                .cep(enderecoDTO.cep())
                .cidade(enderecoDTO.cidade())
                .uf(enderecoDTO.uf())
                .complemento(enderecoDTO.complemento())
                .numero(enderecoDTO.numero())
                .build();

        var createMedico = Medico.builder()
                .nome(medicoDTO.nome())
                .email(medicoDTO.email())
                .telefone(medicoDTO.telefone())
                .crm(medicoDTO.crm())
                .especialidade(medicoDTO.especialidade())
                .endereco(endereco)
                .build();

        medicoRepository.save(createMedico);

        var uri = uriComponentsBuilder.path("/medico/{id}").buildAndExpand(createMedico.getId()).toUri();

        return ResponseEntity.created(uri).body(new MedicoDTO(createMedico));
    }

    @GetMapping
    public ResponseEntity<Page<ListarMedicosDTO>> listagemDeMedicos(
            @PageableDefault(size = 10, sort = { "nome" }) Pageable paginacaoPageable) {
        var listarMedicos = medicoRepository.findAllByAtivoTrue(paginacaoPageable).map(ListarMedicosDTO::new);
        return ResponseEntity.ok(listarMedicos);
    }

    @PutMapping
    @Transactional
    public ResponseEntity<MedicoDTO> atualizar(
            @RequestBody @Valid AtualizarDadosMedicoDTO atualizarDadosMedicoDTO) {
        var medico = medicoRepository.getReferenceById(atualizarDadosMedicoDTO.id());
        medico.atualizarMedicos(atualizarDadosMedicoDTO);
        return ResponseEntity.ok(new MedicoDTO(medico));
    }

    // @DeleteMapping("/{id}")
    // @Transactional
    // public void deletarMedico(@PathVariable UUID id){
    // medicoRepository.deleteById(id);
    // }

    @DeleteMapping("/{id}")
    @Transactional
    public ResponseEntity<Object> deletarMedico(@PathVariable UUID id) {
        var medico = medicoRepository.getReferenceById(id);
        medico.excluir();
        return ResponseEntity.noContent().build();
    }

    @GetMapping("/{id}")
    public ResponseEntity<Object> detalharMedico(@PathVariable UUID id) {
        var medico = medicoRepository.getReferenceById(id);
        return ResponseEntity.ok(new MedicoDTO(medico));
    }
    
}
