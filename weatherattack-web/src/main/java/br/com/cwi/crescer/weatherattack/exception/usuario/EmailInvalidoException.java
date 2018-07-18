package br.com.cwi.crescer.weatherattack.exception.usuario;

public class EmailInvalidoException extends RuntimeException {

    public EmailInvalidoException() {
        super("Email nulo ou invalido.");
    }
}
