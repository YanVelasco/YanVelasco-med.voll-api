package br.com.yanvelasco.api.domain.consultas.validations;

import java.time.Duration;
import java.time.LocalDateTime;

import br.com.yanvelasco.api.domain.consultas.dto.ConsultaDTO;
import br.com.yanvelasco.api.infra.exceptions.TempoMinimoAgendaConsultaException;

public class Validador30MinutosAntecedencia {
    
    public void execute(ConsultaDTO consultaDTO){
        var dataConsulta = consultaDTO.data();
        var agora = LocalDateTime.now();
        var diferencaEmMinutos = Duration.between(agora, dataConsulta).toMinutes();

        if (diferencaEmMinutos < 30) {
            throw new TempoMinimoAgendaConsultaException("Você só pode marcar uma consulta com 30 minutos de antecedência");
        }
    }

}
