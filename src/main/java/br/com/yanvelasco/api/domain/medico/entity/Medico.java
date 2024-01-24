package br.com.yanvelasco.api.domain.medico.entity;

import java.util.UUID;

import br.com.yanvelasco.api.domain.endereco.entity.Endereco;
import br.com.yanvelasco.api.domain.medico.dto.AtualizarDadosMedicoDTO;
import jakarta.persistence.Embedded;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Table(name = "medicos")
@Entity(name = "medico")
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Medico {
    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private UUID id;
    private String nome;
    private String email;
    private String telefone;
    private String crm;
    @Enumerated(EnumType.STRING)
    private Especialidade especialidade;
    @Embedded
    private Endereco endereco;
    @Builder.Default
    private Boolean ativo = true;

    public void atualizarMedicos(AtualizarDadosMedicoDTO atualizarDadosMedicoDTO){
        if (atualizarDadosMedicoDTO.nome() != null) {
            this.nome = atualizarDadosMedicoDTO.nome();
        }
        if (atualizarDadosMedicoDTO.telefone() != null) {
            this.telefone = atualizarDadosMedicoDTO.telefone();
        }
        if (atualizarDadosMedicoDTO.enderecoDTO() != null) {
            this.endereco.atulizarDadosEndereco(atualizarDadosMedicoDTO.enderecoDTO());
        }
    }

    public void excluir(){
        ativo = false;
    }
}
