package br.com.cwi.crescer.weatherattack.exception.usuario;

public class SenhaInvalidaException extends RuntimeException {
    public SenhaInvalidaException() {
        super("Senha inválida.");
    }
}
