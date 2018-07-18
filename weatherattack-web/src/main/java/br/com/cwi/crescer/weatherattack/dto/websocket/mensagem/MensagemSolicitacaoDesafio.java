package br.com.cwi.crescer.weatherattack.dto.websocket.mensagem;

import lombok.Data;

@Data
public class MensagemSolicitacaoDesafio {
    private String mensagem;
    private String idDesafio;
}
