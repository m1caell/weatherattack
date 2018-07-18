package br.com.cwi.crescer.weatherattack.combateTests;

import br.com.cwi.crescer.weatherattack.dominio.Combate;
import br.com.cwi.crescer.weatherattack.dominio.Personagem;
import br.com.cwi.crescer.weatherattack.dominio.Usuario;
import br.com.cwi.crescer.weatherattack.dto.response.PersonagemDtoResponse;
import br.com.cwi.crescer.weatherattack.dto.response.UsuarioDtoResponse;
import br.com.cwi.crescer.weatherattack.dto.websocket.AtaqueModel;
import br.com.cwi.crescer.weatherattack.dto.websocket.ChannelModel;
import br.com.cwi.crescer.weatherattack.dto.websocket.mensagem.MensagemAtaque;
import br.com.cwi.crescer.weatherattack.dto.websocket.mensagem.MensagemCombate;
import br.com.cwi.crescer.weatherattack.security.UserPrincipal;
import br.com.cwi.crescer.weatherattack.service.channel.ChannelService;
import br.com.cwi.crescer.weatherattack.service.combate.RealizarAtaqueService;
import br.com.cwi.crescer.weatherattack.service.poderes.UsarPoderService;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.runners.MockitoJUnitRunner;
import org.springframework.security.core.GrantedAuthority;

import java.time.LocalDateTime;
import java.util.Collection;

import static org.mockito.Matchers.any;

@RunWith(MockitoJUnitRunner.class)
public class RealizarAtaqueServiceTest {

    @InjectMocks
    private RealizarAtaqueService tested;

    @Mock
    private ChannelService channelService;

    @Mock
    private UsarPoderService usarPoderService;

    private Collection<? extends GrantedAuthority> authorities;

    @Test
    public void deveRetornarNullSeNaoForVezDoUsuario(){
        MensagemAtaque mensagemAtaque = new MensagemAtaque();
        mensagemAtaque.setIdPoder(1L);
        mensagemAtaque.setTemperatura(20L);
        UserPrincipal userPrincipal = new UserPrincipal(1L, "teste", "teste", "teste", "teste", authorities);

        UsuarioDtoResponse usuario = new UsuarioDtoResponse();
        usuario.setId(1L);
        PersonagemDtoResponse personagem = new PersonagemDtoResponse(usuario);
        UsuarioDtoResponse usuario1 = new UsuarioDtoResponse();
        usuario.setId(2L);
        PersonagemDtoResponse personagem1 = new PersonagemDtoResponse(usuario1);
        Combate combate = new Combate();
        combate.setFinalidado(false);
        combate.setIdJogadorDaVez(2L);
        combate.setPersonagem1(personagem);
        combate.setPersonagem2(personagem1);
        ChannelModel channelModel = new ChannelModel("11tt", combate, LocalDateTime.now());

        Mockito.when(channelService.procurarPorId("11tt")).thenReturn(channelModel);
        MensagemCombate mensagemCombate = tested.atacar("11tt", mensagemAtaque, userPrincipal);
        Assert.assertNull(mensagemCombate);
    }

    @Test
    public void devePassarPorUsarPoderServiceQuandoForSuaVez(){
        MensagemAtaque mensagemAtaque = new MensagemAtaque();
        mensagemAtaque.setIdPoder(1L);
        mensagemAtaque.setTemperatura(20L);
        UserPrincipal userPrincipal = new UserPrincipal(1L, "teste", "teste", "teste", "teste", authorities);

        UsuarioDtoResponse usuario = new UsuarioDtoResponse();
        usuario.setId(1L);
        PersonagemDtoResponse personagem = new PersonagemDtoResponse(usuario);
        UsuarioDtoResponse usuario1 = new UsuarioDtoResponse();
        usuario.setId(2L);
        PersonagemDtoResponse personagem1 = new PersonagemDtoResponse(usuario1);
        Combate combate = new Combate();
        combate.setFinalidado(false);
        combate.setIdJogadorDaVez(1L);
        combate.setPersonagem1(personagem);
        combate.setPersonagem2(personagem1);
        ChannelModel channelModel = new ChannelModel("11tt", combate, LocalDateTime.now());
        AtaqueModel ataqueModel = new AtaqueModel(personagem, personagem1);

        Mockito.when(channelService.procurarPorId("11tt")).thenReturn(channelModel);
        tested.atacar("11tt", mensagemAtaque, userPrincipal);
        Mockito.verify(usarPoderService, Mockito.times(1)).usarPoder(any(), any(), any());
    }
}
