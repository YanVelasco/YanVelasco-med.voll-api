package med.voll.api.domain.medico.Controller;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import jakarta.transaction.Transactional;
import med.voll.api.domain.endereco.Endereco;
import med.voll.api.domain.endereco.EnderecoDTO;
import med.voll.api.domain.medico.dto.CriarMedicoDTO;
import med.voll.api.domain.medico.entity.Medico;
import med.voll.api.domain.medico.repository.MedicoRepository;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

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
                .crm(criarMedicoDTO.crm())
                .especialidade(criarMedicoDTO.especialidade())
                .endereco(endereco)
                .build();

        return medicoRepository.save(createMedico);
    }
}
