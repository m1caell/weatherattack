package br.com.cwi.crescer.weatherattack.exception.channel;

public class ChannelNaoEncontradoException extends RuntimeException{
    public ChannelNaoEncontradoException() {
        super("Channel não encontrado.");
    }
}
