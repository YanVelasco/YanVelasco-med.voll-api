package br.com.yanvelasco.api.domain.paciente.repository;

import java.util.Optional;
import java.util.UUID;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

import br.com.yanvelasco.api.domain.paciente.entity.Paciente;
import jakarta.validation.constraints.NotNull;

public interface PacienteRepository extends JpaRepository<Paciente, UUID>{
    Page<Paciente> findAllByAtivoTrue(Pageable paginacaoPageable);

    Optional<Paciente> findByNomeOrEmail(String nome, String email);

    Optional<Paciente> findAtivoById(@NotNull UUID idPaciente);
}
