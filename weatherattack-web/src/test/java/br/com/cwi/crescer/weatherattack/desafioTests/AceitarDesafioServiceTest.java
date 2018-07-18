package br.com.cwi.crescer.weatherattack.desafioTests;

import br.com.cwi.crescer.weatherattack.dominio.Desafio;
import br.com.cwi.crescer.weatherattack.dominio.Usuario;
import br.com.cwi.crescer.weatherattack.dto.response.UsuarioDtoResponse;
import br.com.cwi.crescer.weatherattack.dto.websocket.ChannelModel;
import br.com.cwi.crescer.weatherattack.dto.websocket.mensagem.MensagemInicioCombate;
import br.com.cwi.crescer.weatherattack.exception.desafio.DesafioInvalidoException;
import br.com.cwi.crescer.weatherattack.mapper.websocket.ChannelModelMapper;
import br.com.cwi.crescer.weatherattack.security.UserPrincipal;
import br.com.cwi.crescer.weatherattack.service.channel.ChannelService;
import br.com.cwi.crescer.weatherattack.service.desafio.AceitarDesafioService;
import br.com.cwi.crescer.weatherattack.service.desafio.ListarDesafiosService;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.*;
import org.mockito.runners.MockitoJUnitRunner;
import org.springframework.messaging.simp.SimpMessagingTemplate;
import org.springframework.security.core.GrantedAuthority;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import static org.mockito.Matchers.any;

@RunWith(MockitoJUnitRunner.class)
public class AceitarDesafioServiceTest {

    @InjectMocks
    private AceitarDesafioService tested;

    @Mock
    private SimpMessagingTemplate simpMessagingTemplate;

    @Mock
    private ListarDesafiosService listarDesafiosService;

    @Mock
    private ChannelModelMapper mapper;

    @Mock
    private ChannelService channelService;

    @Captor
    ArgumentCaptor<MensagemInicioCombate> captador;

    private Collection<? extends GrantedAuthority> authorities;

    @Test
    public void naoDeveRetornarErro(){
        UserPrincipal userPrincipal = new UserPrincipal(1L, "teste", "teste", "teste", "teste", authorities);
        UsuarioDtoResponse usuario = new UsuarioDtoResponse();
        usuario.setId(1L);
        UsuarioDtoResponse usuario1 = new UsuarioDtoResponse();
        usuario.setId(2L);
        List<Desafio> lista = new ArrayList<>();
        Desafio desafio = new Desafio(usuario, usuario1);
        desafio.getDesafiado().setId(1L);
        lista.add(desafio);
        Mockito.when(listarDesafiosService.getDesafios()).thenReturn(lista);
        Mockito.doNothing().when(simpMessagingTemplate).convertAndSend(any(), captador.capture());

        MensagemInicioCombate mensagemInicioCombate = tested.aceitarDesafio(desafio.getId(), userPrincipal);
        MensagemInicioCombate capturado = captador.getValue();
        Assert.assertEquals(capturado.getChannel(), mensagemInicioCombate.getChannel());
    }

    @Test(expected = DesafioInvalidoException.class)
    public void deveMostrarErroCasoOUsuarioAtualNaoForODesafiado(){
        UserPrincipal userPrincipal = new UserPrincipal(2L, "teste", "teste", "teste", "teste", authorities);
        UsuarioDtoResponse usuario = new UsuarioDtoResponse();
        usuario.setId(1L);
        UsuarioDtoResponse usuario1 = new UsuarioDtoResponse();
        usuario.setId(2L);
        List<Desafio> lista = new ArrayList<>();
        Desafio desafio = new Desafio(usuario, usuario1);
        lista.add(desafio);
        Mockito.when(listarDesafiosService.getDesafios()).thenReturn(lista);

        MensagemInicioCombate mensagemInicioCombate = tested.aceitarDesafio(desafio.getId(), userPrincipal);
    }
}
