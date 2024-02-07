package br.com.yanvelasco.api.infra.exceptions;

public class ValidadorPacienteConsultaNoMesmoDia extends RuntimeException{
    public ValidadorPacienteConsultaNoMesmoDia(String mesage){
        super(mesage);
    }
}
