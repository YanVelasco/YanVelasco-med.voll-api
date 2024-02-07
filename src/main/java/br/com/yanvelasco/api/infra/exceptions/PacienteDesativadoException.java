package br.com.yanvelasco.api.infra.exceptions;

public class PacienteDesativadoException extends RuntimeException{
    public PacienteDesativadoException(String message){
        super(message);
    }
}
