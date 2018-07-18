package br.com.cwi.crescer.weatherattack.dto.websocket.mensagem;

import br.com.cwi.crescer.weatherattack.dto.websocket.ChannelModel;
import lombok.AllArgsConstructor;
import lombok.Data;

import java.time.LocalDateTime;
import java.time.LocalTime;

@Data
@AllArgsConstructor
public class MensagemInicioCombate {
    private String mensagem;
    private ChannelModel channel;
}
