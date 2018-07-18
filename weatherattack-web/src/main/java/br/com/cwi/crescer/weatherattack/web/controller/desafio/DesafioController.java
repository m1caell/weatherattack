package br.com.cwi.crescer.weatherattack.web.controller.desafio;

import br.com.cwi.crescer.weatherattack.dto.websocket.mensagem.MensagemInicioCombate;
import br.com.cwi.crescer.weatherattack.dto.websocket.mensagem.MensagemSolicitacaoDesafio;
import br.com.cwi.crescer.weatherattack.service.desafio.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import br.com.cwi.crescer.weatherattack.dto.request.DesafioDto;
import br.com.cwi.crescer.weatherattack.security.UserPrincipal;

@RestController
@RequestMapping("/desafio")
public class DesafioController {

    @Autowired
    private ProporDesafioService proporDesafioService;

    @Autowired
    private AceitarDesafioService aceitarDesafioService;

    @Autowired
    private RecusarDesafioService recusarDesafioService;

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public MensagemSolicitacaoDesafio proporDesafio(@RequestBody DesafioDto request) {
        return proporDesafioService.propor(request);
    }

    @PostMapping("/{id}")
    @ResponseStatus(HttpStatus.OK)
    public MensagemInicioCombate aceitarDesafio(@PathVariable String id, @AuthenticationPrincipal UserPrincipal user) {
       return aceitarDesafioService.aceitarDesafio(id, user);
    }

    @PostMapping("/recusar/{id}")
    @ResponseStatus(HttpStatus.OK)
    public String recusarDesafio(@PathVariable String id, @AuthenticationPrincipal UserPrincipal user) {
        return recusarDesafioService.recusarDesafio(id, user);
    }
}
