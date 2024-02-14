package br.com.yanvelasco.api.domain.consultas.entity;

import java.time.LocalDateTime;
import java.util.UUID;

import br.com.yanvelasco.api.domain.medico.entity.Medico;
import br.com.yanvelasco.api.domain.paciente.entity.Paciente;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Table(name = "consultas")
@Entity(name = "Consulta")
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class ConsultaEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private UUID id;
    @ManyToOne
    @JoinColumn(name = "medico_id")
    private Medico medico;
    @ManyToOne
    @JoinColumn(name = "paciente_id")
    private Paciente paciente;
    private LocalDateTime data;
}
