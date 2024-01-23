package br.com.yanvelasco.api.infra.exceptions;

public class UserAlreadyExists extends RuntimeException {
    
    public UserAlreadyExists(String mensagem){
       super(mensagem);
    }

}
