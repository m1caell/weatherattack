package br.com.cwi.crescer.weatherattack.dto.websocket.mensagem;

import br.com.cwi.crescer.weatherattack.dominio.enumerated.TipoMensagemCombate;
import lombok.Data;

@Data
public class MensagemAtaque implements  MensagemCombate {
    private TipoMensagemCombate tipo = TipoMensagemCombate.ATAQUE;
    private Long temperatura;
    private Long idPoder;

}
