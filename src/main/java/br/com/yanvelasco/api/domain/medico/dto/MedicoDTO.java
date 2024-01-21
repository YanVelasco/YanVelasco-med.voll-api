package br.com.yanvelasco.api.domain.medico.dto;

import jakarta.validation.Valid;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;
import br.com.yanvelasco.api.domain.endereco.EnderecoDTO;
import br.com.yanvelasco.api.domain.medico.entity.Especialidade;
import io.micrometer.common.lang.NonNull;

public record MedicoDTO(
                @NotBlank String nome,
                @NotBlank @Email String email,
                @NotBlank String telefone,
                @NotBlank @Pattern(regexp = "\\d{4,6}") String crm,
                @NonNull Especialidade especialidade,
                @NonNull @Valid EnderecoDTO enderecoDTO) {
}
