package br.com.cwi.crescer.weatherattack.service.desafio;

import br.com.cwi.crescer.weatherattack.dominio.Desafio;
import br.com.cwi.crescer.weatherattack.security.UserPrincipal;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.messaging.simp.SimpMessagingTemplate;
import org.springframework.stereotype.Service;

@Service
public class RecusarDesafioService {

    @Autowired
    private SimpMessagingTemplate simpMessagingTemplate;

    @Autowired
    private ListarDesafiosService listarDesafiosService;

    public String recusarDesafio(String id, UserPrincipal user){
        Desafio desafio = listarDesafiosService.getDesafios().stream().filter(c -> c.getId().equals(id)).findFirst().get();
        desafio.recusarDesafio();
        Long idDesafiante = desafio.getDesafiante().getId();

        String mensagem = "DESAFIO recusado por: " + desafio.getDesafiado().getApelido();
        simpMessagingTemplate.convertAndSend("/topic/challengeRefuse." + idDesafiante, mensagem);
        return mensagem;
    }

}
