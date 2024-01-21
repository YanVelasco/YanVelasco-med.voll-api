package br.com.yanvelasco.api.domain.paciente.repository;

import java.util.UUID;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

import br.com.yanvelasco.api.domain.paciente.entity.Paciente;

public interface PacienteRepository extends JpaRepository<Paciente, UUID>{
    Page<Paciente> findAllByAtivoTrue(Pageable paginacaoPageable);
}