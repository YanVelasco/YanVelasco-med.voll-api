package br.com.yanvelasco.api.infra.exceptions;

public class UserNotFound extends RuntimeException {
    public UserNotFound(String mensagem) {
        super(mensagem);
    }
}