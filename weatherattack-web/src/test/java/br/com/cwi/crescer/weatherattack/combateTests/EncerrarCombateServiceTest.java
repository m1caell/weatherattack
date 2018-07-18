package br.com.cwi.crescer.weatherattack.combateTests;

import br.com.cwi.crescer.weatherattack.dominio.Combate;
import br.com.cwi.crescer.weatherattack.dominio.Personagem;
import br.com.cwi.crescer.weatherattack.dominio.Usuario;
import br.com.cwi.crescer.weatherattack.dominio.enumerated.TipoMensagemCombate;
import br.com.cwi.crescer.weatherattack.dto.response.PersonagemDtoResponse;
import br.com.cwi.crescer.weatherattack.dto.response.UsuarioDtoResponse;
import br.com.cwi.crescer.weatherattack.dto.websocket.AtaqueModel;
import br.com.cwi.crescer.weatherattack.dto.websocket.ChannelModel;
import br.com.cwi.crescer.weatherattack.dto.websocket.mensagem.MensagemCombate;
import br.com.cwi.crescer.weatherattack.dto.websocket.mensagem.MensagemFim;
import br.com.cwi.crescer.weatherattack.service.combate.EncerrarCombateService;
import br.com.cwi.crescer.weatherattack.service.logCombate.AdicionarLogCombateService;
import br.com.cwi.crescer.weatherattack.service.personagem.AlterarPersonagemService;
import br.com.cwi.crescer.weatherattack.service.personagem.BuscarPersonagemService;
import br.com.cwi.crescer.weatherattack.service.usuario.BuscarUsuarioPorIdService;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.runners.MockitoJUnitRunner;

import java.time.LocalDateTime;

@RunWith(MockitoJUnitRunner.class)
public class EncerrarCombateServiceTest {

    @InjectMocks
    private EncerrarCombateService tested;

    @Mock
    private AdicionarLogCombateService adicionarLogCombateService;

    @Mock
    private AlterarPersonagemService alterarPersonagemService;

    @Mock
    private BuscarUsuarioPorIdService buscarUsuarioPorIdService;

    @Mock
    private BuscarPersonagemService buscarPersonagemService;

    @Test
    public void deveRetornarMensagemFim(){
        UsuarioDtoResponse usuarioDto = new UsuarioDtoResponse();
        usuarioDto.setId(1L);
        Usuario usuario = new Usuario();
        usuario.setId(1L);
        PersonagemDtoResponse personagemDto = new PersonagemDtoResponse(usuarioDto);
        personagemDto.setId(1L);
        Personagem personagem = new Personagem(usuario);
        personagem.setId(1L);

        UsuarioDtoResponse usuarioDto1 = new UsuarioDtoResponse();
        usuarioDto1.setId(2L);
        Usuario usuario1 = new Usuario();
        usuario.setId(2L);
        PersonagemDtoResponse personagemDto1 = new PersonagemDtoResponse(usuarioDto1);
        personagemDto1.setId(2L);
        Personagem personagem1 = new Personagem(usuario1);
        personagem1.setId(2L);

        Combate combate = new Combate();
        combate.setFinalidado(false);
        combate.setIdJogadorDaVez(1L);
        combate.setPersonagem1(personagemDto);
        combate.setPersonagem2(personagemDto1);
        AtaqueModel ataqueModel = new AtaqueModel(personagemDto, personagemDto1);
        ChannelModel channelModel = new ChannelModel("1", combate, LocalDateTime.now());
        LocalDateTime dataFim = LocalDateTime.now().plusSeconds(60);

        Mockito.when(buscarUsuarioPorIdService.buscarPorId(1L)).thenReturn(usuario);
        Mockito.when(buscarUsuarioPorIdService.buscarPorId(2L)).thenReturn(usuario1);

        Mockito.when(buscarPersonagemService.buscarPersonagemPorId(1L)).thenReturn(personagem);
        Mockito.when(buscarPersonagemService.buscarPersonagemPorId(2L)).thenReturn(personagem1);

        MensagemCombate mensagemFimRetornada = tested.encerrar(channelModel, personagemDto, personagemDto1, dataFim, ataqueModel);


        Assert.assertEquals(TipoMensagemCombate.FIM, mensagemFimRetornada.getTipo());

    }
}
