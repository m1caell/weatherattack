package br.com.cwi.crescer.weatherattack.dominio;

import br.com.cwi.crescer.weatherattack.dto.response.PersonagemDtoResponse;
import lombok.Data;

@Data
public class Combate {
    //private Long idCombate;
    private PersonagemDtoResponse personagem1;
    private PersonagemDtoResponse personagem2;
    private Long idJogadorDaVez;
    private boolean finalidado;
}
