package br.com.cwi.crescer.weatherattack.mapper.websocket;

import static java.util.Objects.isNull;

import br.com.cwi.crescer.weatherattack.dominio.Combate;
import br.com.cwi.crescer.weatherattack.dominio.Personagem;
import br.com.cwi.crescer.weatherattack.dto.response.PersonagemDtoResponse;
import br.com.cwi.crescer.weatherattack.service.personagem.BuscarPersonagemPorIdUsuarioService;
import br.com.cwi.crescer.weatherattack.service.personagem.BuscarPersonagemResponseService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import br.com.cwi.crescer.weatherattack.dominio.Desafio;
import br.com.cwi.crescer.weatherattack.dto.websocket.ChannelModel;

import java.time.LocalDateTime;

@Component
public class ChannelModelMapper {

    @Autowired
    private BuscarPersonagemPorIdUsuarioService buscarPersonagemPorIdUsuarioService;

    @Autowired
    private BuscarPersonagemResponseService buscarPersonagemResponseService;

    public ChannelModel mapear(final Desafio from) {

        if (isNull(from)) {
            return null;
        }

        Combate combate = new Combate();
        Personagem p1 = buscarPersonagemPorIdUsuarioService.buscarPersonagemPorIdUsuario(from.getDesafiante().getId());
        PersonagemDtoResponse desafiante = buscarPersonagemResponseService.buscarPersonagemPorId(p1.getId());
        Personagem p2 = buscarPersonagemPorIdUsuarioService.buscarPersonagemPorIdUsuario(from.getDesafiado().getId());
        PersonagemDtoResponse desafiado = buscarPersonagemResponseService.buscarPersonagemPorId(p2.getId());

        combate.setPersonagem1(desafiante);
        desafiante.setVida(100L);
        desafiante.setMana(100L);
        combate.setPersonagem2(desafiado);
        combate.getPersonagem2().setVida(100L);
        combate.getPersonagem2().setMana(100L);
        combate.setIdJogadorDaVez(desafiante.getUsuario().getId());
        combate.setFinalidado(false);

        return ChannelModel.builder()
            .id(from.getId()).combate(combate).dataInicio(LocalDateTime.now())
            .build();
    }

}
