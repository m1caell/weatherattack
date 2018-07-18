package br.com.cwi.crescer.weatherattack.dto.response;

import br.com.cwi.crescer.weatherattack.dominio.Condicao;
import br.com.cwi.crescer.weatherattack.dominio.Poder;
import br.com.cwi.crescer.weatherattack.dominio.Regra;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class PoderDtoResponse {

    public Poder poderes;

    public Regra regras;

    public Condicao condicoes;
}
