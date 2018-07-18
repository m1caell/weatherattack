package br.com.cwi.crescer.weatherattack.dto.websocket;

import br.com.cwi.crescer.weatherattack.dominio.Combate;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Data
@Builder
public class ChannelModel {

    private String id;

    private Combate combate;

    private LocalDateTime dataInicio;

    public ChannelModel(String id, Combate combate, LocalDateTime dataInicio) {
        this.id = id;
        this.combate = combate;
        this.dataInicio = dataInicio;
    }
}
