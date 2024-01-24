package br.com.yanvelasco.api.domain.medico.dto;

import jakarta.validation.Valid;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;

import java.util.UUID;

import br.com.yanvelasco.api.domain.endereco.dto.EnderecoDTO;
import br.com.yanvelasco.api.domain.medico.entity.Especialidade;
import br.com.yanvelasco.api.domain.medico.entity.Medico;

public record MedicoDTO(

        UUID id,
        @NotBlank String nome,
        @NotBlank @Email String email,
        @NotBlank String telefone,
        @NotBlank @Pattern(regexp = "\\d{4,6}") String crm,
        @NotNull Especialidade especialidade,
        @NotNull @Valid EnderecoDTO enderecoDTO) {

    public MedicoDTO(Medico medico) {
        this(medico.getId(),medico.getNome(), medico.getEmail(), medico.getTelefone(), medico.getCrm(), medico.getEspecialidade(),
                new EnderecoDTO(
                        medico.getEndereco().getLogradouro(),
                        medico.getEndereco().getBairro(),
                        medico.getEndereco().getCep(),
                        medico.getEndereco().getCidade(),
                        medico.getEndereco().getUf(),
                        medico.getEndereco().getComplemento(),
                        medico.getEndereco().getNumero()));

    }
}
