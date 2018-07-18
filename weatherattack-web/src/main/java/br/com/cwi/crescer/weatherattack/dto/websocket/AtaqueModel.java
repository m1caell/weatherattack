package br.com.cwi.crescer.weatherattack.dto.websocket;

import br.com.cwi.crescer.weatherattack.dominio.Personagem;
import br.com.cwi.crescer.weatherattack.dto.response.PersonagemDtoResponse;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;

@Builder
@Getter
@AllArgsConstructor
public class AtaqueModel {

    private PersonagemDtoResponse atacante;

    private PersonagemDtoResponse atacado;

}
