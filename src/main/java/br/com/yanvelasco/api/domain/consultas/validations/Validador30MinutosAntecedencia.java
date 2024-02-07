package br.com.yanvelasco.api.domain.consultas.validations;

import java.time.Duration;
import java.time.LocalDateTime;

import org.springframework.stereotype.Component;

import br.com.yanvelasco.api.domain.consultas.dto.ConsultaDTO;
import br.com.yanvelasco.api.infra.exceptions.TempoMinimoAgendaConsultaException;

@Component
public class Validador30MinutosAntecedencia implements ValidadorAgendamentoDeConsulta{
    
    public void execute(ConsultaDTO consultaDTO){
        var dataConsulta = consultaDTO.data();
        var agora = LocalDateTime.now();
        var diferencaEmMinutos = Duration.between(agora, dataConsulta).toMinutes();

        if (diferencaEmMinutos < 30) {
            throw new TempoMinimoAgendaConsultaException("Você só pode marcar uma consulta com 30 minutos de antecedência");
        }
    }

}
