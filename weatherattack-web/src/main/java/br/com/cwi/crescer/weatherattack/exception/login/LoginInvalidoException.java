package br.com.cwi.crescer.weatherattack.exception.login;

public class LoginInvalidoException extends RuntimeException {
    public LoginInvalidoException(String message) {
        super(message);
    }
}
