package br.com.cwi.crescer.weatherattack.service.desafio;

import br.com.cwi.crescer.weatherattack.dominio.Desafio;
import br.com.cwi.crescer.weatherattack.dto.websocket.ChannelModel;
import br.com.cwi.crescer.weatherattack.dto.websocket.mensagem.MensagemInicioCombate;
import br.com.cwi.crescer.weatherattack.exception.desafio.DesafioInvalidoException;
import br.com.cwi.crescer.weatherattack.mapper.websocket.ChannelModelMapper;
import br.com.cwi.crescer.weatherattack.security.UserPrincipal;
import br.com.cwi.crescer.weatherattack.service.channel.ChannelService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.messaging.simp.SimpMessagingTemplate;
import org.springframework.stereotype.Service;

@Service
public class AceitarDesafioService {

    @Autowired
    private SimpMessagingTemplate simpMessagingTemplate;

    @Autowired
    private ListarDesafiosService listarDesafiosService;

    @Autowired
    private ChannelModelMapper mapper;

    @Autowired
    private ChannelService channelService;

    public MensagemInicioCombate aceitarDesafio(String id, UserPrincipal user){
        Desafio desafio = listarDesafiosService.getDesafios().stream().filter(c -> c.getId().equals(id)).findFirst().get();

        if(desafio.getDesafiado().getId() != user.getId())
            throw new DesafioInvalidoException("Desafio inválido. O desafiado não é o mesmo que o usuário logado");

        desafio.aceitarDesafio();
        ChannelModel channel = mapper.mapear(desafio);

        Long idDesafiante = desafio.getDesafiante().getId();
        String mensagem = "DESAFIO aceito por: " + desafio.getDesafiado().getApelido();
        MensagemInicioCombate mensagemInicioCombate = new MensagemInicioCombate(mensagem, channel);

        simpMessagingTemplate.convertAndSend("/topic/challengeAccepted." + idDesafiante, mensagemInicioCombate);

        channelService.salvarChannel(channel);
        return mensagemInicioCombate;
    }
}
