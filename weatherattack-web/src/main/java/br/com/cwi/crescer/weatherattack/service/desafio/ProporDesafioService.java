package br.com.cwi.crescer.weatherattack.service.desafio;

import br.com.cwi.crescer.weatherattack.dominio.Desafio;
import br.com.cwi.crescer.weatherattack.dto.request.DesafioDto;
import br.com.cwi.crescer.weatherattack.dto.websocket.mensagem.MensagemSolicitacaoDesafio;
import br.com.cwi.crescer.weatherattack.exception.desafio.DesafioInvalidoException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.messaging.simp.SimpMessagingTemplate;
import org.springframework.stereotype.Service;

@Service
public class ProporDesafioService {

    @Autowired
    private SimpMessagingTemplate simpMessagingTemplate;

    @Autowired
    private DesafiarUsuarioService desafiarUsuarioService;

    public MensagemSolicitacaoDesafio propor(DesafioDto request){
        Long idDesafiante = request.getIdDesafiante();
        Long idDesafiado = request.getIdDesafiado();

        if(idDesafiado == idDesafiante) throw new DesafioInvalidoException("Não é possível realizar um desafio consigo mesmo.");

        Desafio desafio = desafiarUsuarioService.desafiarUsuario(idDesafiante, idDesafiado);
        MensagemSolicitacaoDesafio mensagem = new MensagemSolicitacaoDesafio();
        mensagem.setMensagem(desafio.getDesafiante().getApelido());
        mensagem.setIdDesafio(desafio.getId());
        simpMessagingTemplate.convertAndSend("/topic/challenge." + idDesafiado, mensagem);

        mensagem.setMensagem("Solicitação de desafio enviado para " + desafio.getDesafiado().getApelido());
        return mensagem;
    }
}
