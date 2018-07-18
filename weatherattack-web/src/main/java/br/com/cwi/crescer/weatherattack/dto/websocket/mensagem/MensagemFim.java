package br.com.cwi.crescer.weatherattack.dto.websocket.mensagem;

import br.com.cwi.crescer.weatherattack.dominio.enumerated.TipoMensagemCombate;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class MensagemFim implements MensagemCombate {
    private TipoMensagemCombate tipo;
    private Long idVencedor;
    private Long idPerdedor;

    public MensagemFim(Long idVencedor,  Long idPerdedor ) {
        this.tipo = TipoMensagemCombate.FIM;
        this.idVencedor = idVencedor;
        this.idPerdedor = idPerdedor;
    }
}
