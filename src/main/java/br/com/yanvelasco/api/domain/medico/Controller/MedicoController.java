package br.com.yanvelasco.api.domain.medico.Controller;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

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
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;

@RestController
@RequestMapping("/medico")
public class MedicoController {

    @Autowired
    private MedicoRepository medicoRepository;

    @PostMapping
    @Transactional
    public Medico criarMedico(@RequestBody @Valid MedicoDTO criarMedicoDTO) {

        EnderecoDTO enderecoDTO = criarMedicoDTO.enderecoDTO();
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
                .nome(criarMedicoDTO.nome())
                .email(criarMedicoDTO.email())
                .telefone(criarMedicoDTO.telefone())
                .crm(criarMedicoDTO.crm())
                .especialidade(criarMedicoDTO.especialidade())
                .endereco(endereco)
                .build();

        return medicoRepository.save(createMedico);
    }

    @GetMapping
    public Page<ListarMedicosDTO> listagemDeMedicos(
            @PageableDefault(size = 10, sort = { "nome" }) Pageable paginacaoPageable) {
        return medicoRepository.findAll(paginacaoPageable).map(ListarMedicosDTO::new);
    }

    @PutMapping
    @Transactional
    public @Valid AtualizarDadosMedicoDTO putMethodName(@RequestBody @Valid AtualizarDadosMedicoDTO atualizarDadosMedicoDTO) {
        var medico = medicoRepository.getReferenceById(atualizarDadosMedicoDTO.id());
        medico.atualizarMedicos(atualizarDadosMedicoDTO);
        return atualizarDadosMedicoDTO;
    }

    // @DeleteMapping("/{id}")
    // @Transactional
    // public void deletarMedico(@PathVariable UUID id){
    //     medicoRepository.deleteById(id);
    // }

    @DeleteMapping("/{id}")
    @Transactional
    public void deletarMedico(@PathVariable UUID id){
        var medico = medicoRepository.getReferenceById(id);
        medico.excluir();
    }

}
