package br.com.yanvelasco.api.domain.paciente.dto;

import java.util.UUID;

import br.com.yanvelasco.api.domain.endereco.dto.EnderecoDTO;
import br.com.yanvelasco.api.domain.paciente.entity.Paciente;
import jakarta.validation.Valid;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;

public record PacienteDTO(
                UUID id,
                @NotBlank String nome,
                @NotBlank @Email String email,
                @NotBlank String telefone,
                @NotBlank @Pattern(regexp = "\\d{11}") String cpf,
                @NotNull @Valid EnderecoDTO enderecoDTO) {

        public PacienteDTO(Paciente paciente) {
                this(paciente.getId() 
                ,paciente.getNome(),
                 paciente.getEmail(),
                  paciente.getTelefone(),
                   paciente.getCpf(),
                                new EnderecoDTO(
                                                paciente.getEndereco().getLogradouro(),
                                                paciente.getEndereco().getBairro(),
                                                paciente.getEndereco().getCep(),
                                                paciente.getEndereco().getCidade(),
                                                paciente.getEndereco().getUf(),
                                                paciente.getEndereco().getComplemento(),
                                                paciente.getEndereco().getNumero()));
        }

}
