package br.com.cwi.crescer.weatherattack.exception.poder;

public class ManaInsuficienteException extends RuntimeException {

    public ManaInsuficienteException() {
        super("Mana insuficiente para usar o poder");
    }
}
