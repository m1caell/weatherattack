package br.com.cwi.crescer.weatherattack.service.poderes;

import br.com.cwi.crescer.weatherattack.dominio.Personagem;
import br.com.cwi.crescer.weatherattack.dominio.Poder;
import br.com.cwi.crescer.weatherattack.dominio.Regra;
import br.com.cwi.crescer.weatherattack.dominio.enumerated.Parametro;
import br.com.cwi.crescer.weatherattack.dto.response.PersonagemDtoResponse;
import br.com.cwi.crescer.weatherattack.dto.websocket.mensagem.MensagemAtaque;
import br.com.cwi.crescer.weatherattack.dto.websocket.mensagem.MensagemCombate;
import br.com.cwi.crescer.weatherattack.dto.websocket.mensagem.MensagemDano;
import br.com.cwi.crescer.weatherattack.dto.websocket.AtaqueModel;
import br.com.cwi.crescer.weatherattack.dto.websocket.ChannelModel;
import br.com.cwi.crescer.weatherattack.exception.poder.ManaInsuficienteException;
import br.com.cwi.crescer.weatherattack.service.combate.EncerrarCombateService;
import br.com.cwi.crescer.weatherattack.service.personagem.AtualizarPersonagemService;
import br.com.cwi.crescer.weatherattack.service.personagem.BuscarPersonagemService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Random;


@Service
public class UsarPoderService {

    @Autowired
    private BuscarPersonagemService buscarPersonagemService;

    @Autowired
    private BuscarPoderPorIdService buscarPoderPorIdService;

    @Autowired
    private AtualizarPersonagemService atualizarPersonagemService;

    @Autowired
    private EncerrarCombateService encerrarCombateService;

    public MensagemCombate usarPoder(ChannelModel channelModel, AtaqueModel ataqueModel, MensagemAtaque mensagemAtaque){

        Poder poder = buscarPoderPorIdService.buscarPoderPorId(mensagemAtaque.getIdPoder());
        Long temperatura = mensagemAtaque.getTemperatura();

        PersonagemDtoResponse atacante = channelModel.getCombate().getPersonagem1().getUsuario().getId() == channelModel.getCombate().getIdJogadorDaVez() ?
                channelModel.getCombate().getPersonagem1() :
                channelModel.getCombate().getPersonagem2();

        PersonagemDtoResponse atacado = channelModel.getCombate().getPersonagem1().getUsuario().getId() != channelModel.getCombate().getIdJogadorDaVez() ?
                channelModel.getCombate().getPersonagem1() :
                channelModel.getCombate().getPersonagem2();


        atacante.setMana(atacante.getMana() - poder.getCustoDeMana());
        if(atacante.getMana() < 0){
            throw new ManaInsuficienteException();
        }

        atacado.setMana(atacado.getMana() + 10 > 100 ? 100 : atacado.getMana() + 10);

        List<Regra> regras = poder.getRegras();
        Long poderAdicionalPorVariacaoClimatica = 0l;
        for(Regra x : regras){
            switch (x.getCondicao().getNome()){
                case TEMPERATURA:
                    if(x.getParametro() == Parametro.MAIOR_OU_IGUAL_A || x.getParametro() == Parametro.MAIOR_QUE){
                        poderAdicionalPorVariacaoClimatica += temperatura - x.getValor().longValue();
                    } else {
                        poderAdicionalPorVariacaoClimatica += Math.abs(x.getValor().longValue() - temperatura);
                    }
                break;
            }
        }

        Long dano = calcularDano(poder.getDanoBase(),
                poderAdicionalPorVariacaoClimatica,
                this.adicionarVariacaoRandomica());

        atacado.setVida((atacado.getVida() - Math.abs(dano)) < 0l ? 0l : (atacado.getVida() - Math.abs(dano)));

        MensagemDano mensagemDano = new MensagemDano();

        mensagemDano.setIdAtacado(ataqueModel.getAtacado().getUsuario().getId());

        mensagemDano.setDano(dano);
        mensagemDano.setVidaRestante(ataqueModel.getAtacado().getVida());
        mensagemDano.setManaRestante(atacante.getMana() );
        mensagemDano.setManaDoAtacado(atacado.getMana());
        channelModel.getCombate().setIdJogadorDaVez(ataqueModel.getAtacado().getUsuario().getId());

        if(ataqueModel.getAtacado().getVida() <= 0){
            LocalDateTime dataFim = LocalDateTime.now();
            
            return encerrarCombateService.encerrar(channelModel, ataqueModel.getAtacante(), ataqueModel.getAtacado(), dataFim, ataqueModel);
        }

        return mensagemDano;
    }

    private Double adicionarVariacaoRandomica(){
        Random random = new Random();
        return random.doubles(1, 0.7, 1.3).findFirst().getAsDouble();
    }

    private Long calcularDano(Long danoBase, Long variacaoClimatica, Double variacaoRandomica){
        Double danoFinal = (danoBase + variacaoClimatica) * variacaoRandomica;
        return danoFinal.longValue();
    }
}
