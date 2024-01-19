package med.voll.api.domain.medico.controller;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import jakarta.transaction.Transactional;
import jakarta.validation.Valid;
import med.voll.api.domain.endereco.Endereco;
import med.voll.api.domain.endereco.EnderecoDTO;
import med.voll.api.domain.medico.dto.CriarMedicoDTO;
import med.voll.api.domain.medico.dto.ListarMedicosDTO;
import med.voll.api.domain.medico.entity.Medico;
import med.voll.api.domain.medico.repository.MedicoRepository;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.GetMapping;

@RestController
@RequestMapping("/medico")
public class MedicoController {

    @Autowired
    private MedicoRepository medicoRepository;

    @PostMapping
    @Transactional
    public Medico criarMedico(@RequestBody @Valid CriarMedicoDTO criarMedicoDTO) {

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
    public List<ListarMedicosDTO> listagemDeMedicos() {
        return medicoRepository.findAll().stream().map(ListarMedicosDTO::new).toList();
    }

}
