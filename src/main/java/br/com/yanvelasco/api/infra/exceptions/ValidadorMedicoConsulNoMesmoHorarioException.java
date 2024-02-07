package br.com.yanvelasco.api.infra.exceptions;

public class ValidadorMedicoConsulNoMesmoHorarioException extends RuntimeException{
    public ValidadorMedicoConsulNoMesmoHorarioException(String message){
        super(message);
    }
}
