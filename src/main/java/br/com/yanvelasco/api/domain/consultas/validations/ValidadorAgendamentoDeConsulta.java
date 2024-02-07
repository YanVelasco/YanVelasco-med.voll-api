package br.com.yanvelasco.api.domain.consultas.validations;

import br.com.yanvelasco.api.domain.consultas.dto.ConsultaDTO;

public interface ValidadorAgendamentoDeConsulta {

    void execute(ConsultaDTO consultaDTO);

}
