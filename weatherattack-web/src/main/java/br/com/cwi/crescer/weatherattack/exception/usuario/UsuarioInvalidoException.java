package br.com.cwi.crescer.weatherattack.exception.usuario;

public class UsuarioInvalidoException extends RuntimeException {

    public UsuarioInvalidoException() {
        super("O Usuário é nulo ou possui campos inválidos");
    }
}
