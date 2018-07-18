package br.com.cwi.crescer.weatherattack.dto.websocket.mensagem;

import br.com.cwi.crescer.weatherattack.dominio.enumerated.TipoMensagemCombate;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@AllArgsConstructor
@Setter
@Getter
public class MensagemDano implements MensagemCombate {
    private TipoMensagemCombate tipo;
    private Long idAtacado;
    private Long dano;
    private Long vidaRestante;
    private Long manaRestante;
    private Long manaDoAtacado;

    public MensagemDano() {
        this.tipo = TipoMensagemCombate.DANO;
    }
}
