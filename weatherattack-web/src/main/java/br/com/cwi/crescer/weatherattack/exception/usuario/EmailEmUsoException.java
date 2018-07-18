package br.com.cwi.crescer.weatherattack.exception.usuario;

public class EmailEmUsoException extends RuntimeException {

    public EmailEmUsoException() {
        super("Este email já está em uso");
    }
}
