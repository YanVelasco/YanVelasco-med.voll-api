package br.com.yanvelasco.api.domain.paciente.entity;

import java.util.UUID;

import br.com.yanvelasco.api.domain.endereco.Endereco;
import br.com.yanvelasco.api.domain.paciente.dto.AtualizarPacienteDTO;
import jakarta.persistence.Embedded;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Table(name = "pacientes")
@Entity(name = "paciente")
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Paciente {
    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private UUID id;
    private String nome;
    private String email;
    private String telefone;
    private String cpf;
    @Embedded
    private Endereco endereco;
    @Builder.Default
    private Boolean ativo = true;

    public void atualizarPaciente(AtualizarPacienteDTO AtualizarPacienteDTO){
        if (AtualizarPacienteDTO.nome() != null) {
            this.nome = AtualizarPacienteDTO.nome();
        }
        if (AtualizarPacienteDTO.telefone() != null) {
            this.telefone = AtualizarPacienteDTO.telefone();
        }
        if (AtualizarPacienteDTO.enderecoDTO() != null) {
            this.endereco.atulizarDadosEndereco(AtualizarPacienteDTO.enderecoDTO());
        }
    }

    public void excluir(){
        ativo = false;
    }
}
