package med.voll.api.domain.medico.dto;

import jakarta.validation.Valid;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;
import io.micrometer.common.lang.NonNull;
import med.voll.api.domain.endereco.EnderecoDTO;
import med.voll.api.domain.medico.entity.Especialidade;

public record CriarMedicoDTO(
                @NotBlank String nome,
                @NotBlank @Email String email,
                @NotBlank String telefone,
                @NotBlank @Pattern(regexp = "\\d{4,6}") String crm,
                @NonNull Especialidade especialidade,
                @NonNull @Valid EnderecoDTO enderecoDTO) {
}
