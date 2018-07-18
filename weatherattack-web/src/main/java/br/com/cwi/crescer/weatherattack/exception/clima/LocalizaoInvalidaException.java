package br.com.cwi.crescer.weatherattack.exception.clima;

public class LocalizaoInvalidaException extends RuntimeException {

    public LocalizaoInvalidaException() {
        super("Localização invalida ou nula.");
    }
}
