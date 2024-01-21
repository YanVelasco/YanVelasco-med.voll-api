package br.com.yanvelasco.api.domain.medico.repository;

import java.util.Optional;
import java.util.UUID;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import br.com.yanvelasco.api.domain.medico.entity.Medico;

@Repository
public interface MedicoRepository extends JpaRepository<Medico, UUID> {
    Optional<Medico> findByNomeOrEmail(String nome, String email);
}