package br.com.yanvelasco.api.domain.consultas.validations;

import java.time.DayOfWeek;

import org.springframework.stereotype.Component;

import br.com.yanvelasco.api.domain.consultas.dto.ConsultaDTO;
import br.com.yanvelasco.api.infra.exceptions.DomingoException;
import br.com.yanvelasco.api.infra.exceptions.HoraQueNaoFuncionaException;

@Component
public class ValidadorHorarioFuncinamentoClinica implements ValidadorAgendamentoDeConsulta{
    
    public void execute(ConsultaDTO consultaDTO){
        var dataConsulta = consultaDTO.data();
        var dataSemanaInvalida = dataConsulta.getDayOfWeek().equals(DayOfWeek.SUNDAY);
        var horarioInvalido = dataConsulta.getHour() < 7 || dataConsulta.getHour() > 18;

        if (dataSemanaInvalida) {
            throw new DomingoException("A clinica não funciona aos domingos");
        }

        if (horarioInvalido) {
            throw new HoraQueNaoFuncionaException("A clinica permite marcações de consultas entre as 7 da manhã e as 18 da noite");
        }
    }

}
