package br.com.yanvelasco.api.domain.medico.repository;

import java.time.LocalDateTime;
import java.util.Optional;
import java.util.UUID;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import br.com.yanvelasco.api.domain.medico.entity.Especialidade;
import br.com.yanvelasco.api.domain.medico.entity.Medico;
import jakarta.validation.constraints.Future;
import jakarta.validation.constraints.NotNull;

@Repository
public interface MedicoRepository extends JpaRepository<Medico, UUID> {
        Optional<Medico> findByNomeOrEmail(String nome, String email);

        Page<Medico> findAllByAtivoTrue(Pageable paginacaoPageable);

        @Query("SELECT m FROM Medico m " +
                        "WHERE m.ativo = true " +
                        "AND m.especialidade = :especialidade " +
                        "AND m.id NOT IN (SELECT c.medico.id FROM Consulta c WHERE c.data = :data) " +
                        "ORDER BY FUNCTION('RAND') " +
                        "LIMIT 1")
        Medico escolherMedicoPorEspecialidade(Especialidade especialidade, LocalDateTime data);

        @Query("""
                        SELECT m.ativo FROM Medico m WHERE m.id= :idMedico
                        """)
        Boolean findAtivoById(UUID idMedico);

        Boolean existsByIdAndData(UUID idMedico, @NotNull @Future LocalDateTime data);
}