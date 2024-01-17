package med.voll.api.domain.medico.entity;

import java.util.UUID;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import med.voll.api.domain.endereco.Endereco;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Medico {
    
    private UUID id;
    private String email;
    private String telefone;
    private String crm;
    private Especialidade especialidade;
    private Endereco endereco;

}
