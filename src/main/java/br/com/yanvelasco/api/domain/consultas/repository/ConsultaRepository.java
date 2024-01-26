package br.com.yanvelasco.api.domain.consultas.repository;

import java.util.UUID;

import org.springframework.data.jpa.repository.JpaRepository;

import br.com.yanvelasco.api.domain.consultas.entity.Consulta;

public interface ConsultaRepository extends JpaRepository<Consulta, UUID>{    
}
