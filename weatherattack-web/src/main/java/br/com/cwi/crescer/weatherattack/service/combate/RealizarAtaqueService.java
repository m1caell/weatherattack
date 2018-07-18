package br.com.cwi.crescer.weatherattack.service.combate;

import br.com.cwi.crescer.weatherattack.dominio.Personagem;
import br.com.cwi.crescer.weatherattack.dto.response.PersonagemDtoResponse;
import br.com.cwi.crescer.weatherattack.dto.websocket.AtaqueModel;
import br.com.cwi.crescer.weatherattack.dto.websocket.ChannelModel;
import br.com.cwi.crescer.weatherattack.dto.websocket.mensagem.MensagemAtaque;
import br.com.cwi.crescer.weatherattack.dto.websocket.mensagem.MensagemCombate;
import br.com.cwi.crescer.weatherattack.security.UserPrincipal;
import br.com.cwi.crescer.weatherattack.service.channel.ChannelService;
import br.com.cwi.crescer.weatherattack.service.poderes.UsarPoderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class RealizarAtaqueService {

    @Autowired
    private ChannelService channelService;

    @Autowired
    private UsarPoderService usarPoderService;

    public MensagemCombate atacar(String channelId, MensagemAtaque mensagemCombateModel, UserPrincipal usuarioLogado){
        ChannelModel channelModel = channelService.procurarPorId(channelId);
        AtaqueModel ataque = getAtaque(channelModel, usuarioLogado.getId());


        if(channelModel.getCombate().getIdJogadorDaVez() != usuarioLogado.getId()){
            return null;
        }

        PersonagemDtoResponse personagem = channelModel.getCombate().getPersonagem1().getUsuario().getId() == channelModel.getCombate().getIdJogadorDaVez() ?
                channelModel.getCombate().getPersonagem1() :
                channelModel.getCombate().getPersonagem2();

        return usarPoderService.usarPoder(channelModel,ataque,mensagemCombateModel);
    }


    private AtaqueModel getAtaque(ChannelModel channel, Long idUsuarioAtacante) {
        if (channel.getCombate().getPersonagem1().getUsuario().getId() == idUsuarioAtacante) {
            return AtaqueModel.builder()
                    .atacante(channel.getCombate().getPersonagem1())
                    .atacado(channel.getCombate().getPersonagem2())
                    .build();
        } else {
            return AtaqueModel.builder()
                    .atacante(channel.getCombate().getPersonagem2())
                    .atacado(channel.getCombate().getPersonagem1())
                    .build();
        }
    }
}
