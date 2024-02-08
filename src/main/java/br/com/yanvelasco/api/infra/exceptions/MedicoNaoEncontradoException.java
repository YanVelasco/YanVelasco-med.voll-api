package br.com.yanvelasco.api.infra.exceptions;

public class MedicoNaoEncontradoException extends RuntimeException{
    public MedicoNaoEncontradoException(String message){
        super(message);
    }
}
