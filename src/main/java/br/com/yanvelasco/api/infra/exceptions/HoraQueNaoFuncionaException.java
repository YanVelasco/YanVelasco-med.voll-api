package br.com.yanvelasco.api.infra.exceptions;

public class HoraQueNaoFuncionaException extends RuntimeException{
    public HoraQueNaoFuncionaException(String message){
        super(message);
    }
}
