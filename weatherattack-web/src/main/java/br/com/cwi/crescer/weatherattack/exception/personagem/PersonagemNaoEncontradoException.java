package br.com.cwi.crescer.weatherattack.exception.personagem;

public class PersonagemNaoEncontradoException extends RuntimeException {

    public PersonagemNaoEncontradoException() {
        super("Personagem não foi encontrado");
    }
}
