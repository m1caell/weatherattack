package br.com.cwi.crescer.weatherattack.web.controller.combate;

import br.com.cwi.crescer.weatherattack.dto.websocket.mensagem.MensagemAtaque;
import br.com.cwi.crescer.weatherattack.dto.websocket.mensagem.MensagemCombate;
import br.com.cwi.crescer.weatherattack.dto.websocket.ChannelModel;
import br.com.cwi.crescer.weatherattack.security.UserPrincipal;
import br.com.cwi.crescer.weatherattack.service.channel.ChannelService;
import br.com.cwi.crescer.weatherattack.service.combate.RealizarAtaqueService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.messaging.handler.annotation.DestinationVariable;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.handler.annotation.Payload;
import org.springframework.messaging.handler.annotation.SendTo;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/combate")
public class CombateController {

    @Autowired
    private ChannelService channelService;

    @Autowired
    RealizarAtaqueService realizarAtaqueService;

    @GetMapping
    public List<ChannelModel> buscarTodos(){
        return channelService.listarTodos();
    }

    @GetMapping("/{idChannel}")
    public ChannelModel buscarChannel(@PathVariable String idChannel ){
        return  channelService.procurarPorId(idChannel);
    }

    @MessageMapping("/challenge.{channelId}")
    @SendTo("/topic/challenge.{channelId}")
    public MensagemCombate atacar(@DestinationVariable String channelId, @Payload MensagemAtaque mensagemCombateModel, @AuthenticationPrincipal UserPrincipal usuarioLogado){
        return realizarAtaqueService.atacar(channelId, mensagemCombateModel, usuarioLogado);
    }
}
