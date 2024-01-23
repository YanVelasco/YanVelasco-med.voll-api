package br.com.yanvelasco.api.infra.exceptions;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import br.com.yanvelasco.api.infra.exceptions.dto.DadosErroVlidacao;
import jakarta.persistence.EntityNotFoundException;

@RestControllerAdvice
public class TratadorDeErros {
    
    @ExceptionHandler(EntityNotFoundException.class)
    public ResponseEntity<Object> error404(){
        return ResponseEntity.notFound().build();
    }

    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<Object> error400(MethodArgumentNotValidException methodArgumentNotValidException){
        var errors = methodArgumentNotValidException.getFieldErrors();
        return ResponseEntity.badRequest().body(errors.stream().map(DadosErroVlidacao::new).toList());
    }
}
