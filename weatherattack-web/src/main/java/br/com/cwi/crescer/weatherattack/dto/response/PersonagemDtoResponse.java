package br.com.cwi.crescer.weatherattack.dto.response;

import br.com.cwi.crescer.weatherattack.dominio.Poder;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;

import java.util.List;

@Data
@Builder
@AllArgsConstructor
public class PersonagemDtoResponse {
    private Long id;
    private UsuarioDtoResponse usuario;
    private Long vida;
    private Long mana;
    private Long battles;
    private Long wins;
    private Long loses;
    private Long coins;
    private List<Poder> poderes;

    public PersonagemDtoResponse(UsuarioDtoResponse usuario) {
        this.usuario = usuario;
        this.vida = 100L;
        this.mana = 100L;
        this.coins = 0L;
        this.battles = 0l;
        this.wins = 0l;
        this.loses = 0l;
    }
}
