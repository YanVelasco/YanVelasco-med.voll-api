package br.com.yanvelasco.api.infra.exceptions.dto;

import org.springframework.validation.FieldError;

public record DadosErroVlidacao(String field, String message) {
    public DadosErroVlidacao(FieldError error){
        this(error.getField(), error.getDefaultMessage());
    }
}
