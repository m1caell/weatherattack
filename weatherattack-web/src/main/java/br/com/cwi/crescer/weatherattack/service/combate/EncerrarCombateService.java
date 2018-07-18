package br.com.cwi.crescer.weatherattack.service.combate;

import br.com.cwi.crescer.weatherattack.dominio.LogCombate;
import br.com.cwi.crescer.weatherattack.dominio.Personagem;
import br.com.cwi.crescer.weatherattack.dominio.Usuario;
import br.com.cwi.crescer.weatherattack.dto.response.PersonagemDtoResponse;
import br.com.cwi.crescer.weatherattack.dto.websocket.mensagem.MensagemCombate;
import br.com.cwi.crescer.weatherattack.dto.websocket.mensagem.MensagemFim;
import br.com.cwi.crescer.weatherattack.dto.websocket.AtaqueModel;
import br.com.cwi.crescer.weatherattack.dto.websocket.ChannelModel;
import br.com.cwi.crescer.weatherattack.service.logCombate.AdicionarLogCombateService;
import br.com.cwi.crescer.weatherattack.service.personagem.AlterarPersonagemService;
import br.com.cwi.crescer.weatherattack.service.personagem.BuscarPersonagemService;
import br.com.cwi.crescer.weatherattack.service.usuario.BuscarUsuarioPorIdService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.Duration;
import java.time.LocalDateTime;

@Service
public class EncerrarCombateService {

    @Autowired
    private AdicionarLogCombateService adicionarLogCombateService;

    @Autowired
    private AlterarPersonagemService alterarPersonagemService;

    @Autowired
    private BuscarUsuarioPorIdService buscarUsuarioPorIdService;

    @Autowired
    private BuscarPersonagemService buscarPersonagemService;

    public MensagemCombate encerrar(ChannelModel c, PersonagemDtoResponse vencedor, PersonagemDtoResponse perdedor, LocalDateTime dataFim, AtaqueModel ataqueModel){


        LogCombate logCombate = new LogCombate();
        Usuario desafiado = buscarUsuarioPorIdService.buscarPorId(c.getCombate().getPersonagem2().getUsuario().getId());
        logCombate.setDesafiado(desafiado);
        Usuario desafiante = buscarUsuarioPorIdService.buscarPorId(c.getCombate().getPersonagem1().getUsuario().getId());
        logCombate.setDesafiante(desafiante);
        Usuario usuarioVencedor = buscarUsuarioPorIdService.buscarPorId(vencedor.getUsuario().getId());
        logCombate.setVencedor(usuarioVencedor);
        logCombate.setIdCombate(c.getId());
        logCombate.setDataInicio(c.getDataInicio());
        logCombate.setDataFim(dataFim);
        Long duracao = Duration.between(c.getDataInicio(), dataFim).getSeconds();
        logCombate.setDuracaoEmSegundos(duracao);
        if(!c.getCombate().isFinalidado()){
            adicionarLogCombateService.salvar(logCombate);
            c.getCombate().setFinalidado(true);
        }

        Personagem personagemVencedor = buscarPersonagemService.buscarPersonagemPorId(vencedor.getId());
        Personagem personagemPerdedor = buscarPersonagemService.buscarPersonagemPorId(perdedor.getId());
        alterarPersonagemService.alterar(personagemVencedor.setValoresVencedor());
        alterarPersonagemService.alterar(personagemPerdedor.setValoresPerdedor());

        return new MensagemFim(ataqueModel.getAtacante().getUsuario().getId(), ataqueModel.getAtacado().getUsuario().getId() );
    }
}
