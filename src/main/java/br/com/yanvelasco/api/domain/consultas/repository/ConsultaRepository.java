package br.com.yanvelasco.api.domain.consultas.repository;

import java.time.LocalDateTime;
import java.util.UUID;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import br.com.yanvelasco.api.domain.consultas.entity.ConsultaEntity;
import jakarta.validation.constraints.Future;
import jakarta.validation.constraints.NotNull;

public interface ConsultaRepository extends JpaRepository<ConsultaEntity, UUID> {

    Boolean existsByMedicoIdAndData(UUID idMedico, @NotNull @Future LocalDateTime data);

    @Query("SELECT CASE WHEN COUNT(c) > 0 THEN TRUE ELSE FALSE END " +
            "FROM Consulta c " +
            "WHERE c.paciente.id = :idPaciente " +
            "AND c.data BETWEEN :primeiroHorario AND :ultimoHorario")
    Boolean existsByPacienteIdAndDataBetween(@Param("idPaciente") UUID idPaciente,
            @Param("primeiroHorario") LocalDateTime primeiroHorario,
            @Param("ultimoHorario") LocalDateTime ultimoHorario);
}
