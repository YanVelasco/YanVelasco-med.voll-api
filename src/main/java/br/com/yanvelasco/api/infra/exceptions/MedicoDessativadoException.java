package br.com.yanvelasco.api.infra.exceptions;

public class MedicoDessativadoException extends RuntimeException{
    public MedicoDessativadoException(String message){
        super(message);
    }
}
