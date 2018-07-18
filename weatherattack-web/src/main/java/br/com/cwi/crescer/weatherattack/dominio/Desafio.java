package br.com.cwi.crescer.weatherattack.dominio;

import static br.com.cwi.crescer.weatherattack.dominio.enumerated.StatusDesafio.ACEITO;
import static br.com.cwi.crescer.weatherattack.dominio.enumerated.StatusDesafio.NAO_ACEITO;
import static br.com.cwi.crescer.weatherattack.dominio.enumerated.StatusDesafio.PENDENTE;

import java.util.UUID;

import br.com.cwi.crescer.weatherattack.dominio.enumerated.StatusDesafio;
import br.com.cwi.crescer.weatherattack.dto.response.UsuarioDtoResponse;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class Desafio {

    private String id;
    private UsuarioDtoResponse desafiante;
    private UsuarioDtoResponse desafiado;
    private StatusDesafio statusDesafio;

    public Desafio(UsuarioDtoResponse desafiante, UsuarioDtoResponse desafiado){
        this.id = UUID.randomUUID().toString();
        this.desafiante = desafiante;
        this.desafiado = desafiado;
        this.statusDesafio = PENDENTE;
    }

    public void aceitarDesafio(){
        this.statusDesafio = ACEITO;
    }

    public void recusarDesafio(){
        this.statusDesafio = NAO_ACEITO;
    }
}
