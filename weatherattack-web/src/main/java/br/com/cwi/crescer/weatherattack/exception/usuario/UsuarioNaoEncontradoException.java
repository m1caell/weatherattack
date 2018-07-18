package br.com.cwi.crescer.weatherattack.exception.usuario;

public class UsuarioNaoEncontradoException extends RuntimeException {

    public UsuarioNaoEncontradoException() {
        super("O Usuário com este id não foi encontrado.");
    }
}
