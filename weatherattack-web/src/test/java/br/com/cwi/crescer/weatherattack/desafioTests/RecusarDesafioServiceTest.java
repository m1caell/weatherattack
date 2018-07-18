package br.com.cwi.crescer.weatherattack.desafioTests;

import br.com.cwi.crescer.weatherattack.dominio.Desafio;
import br.com.cwi.crescer.weatherattack.dominio.Usuario;
import br.com.cwi.crescer.weatherattack.dto.response.UsuarioDtoResponse;
import br.com.cwi.crescer.weatherattack.dto.websocket.mensagem.MensagemInicioCombate;
import br.com.cwi.crescer.weatherattack.security.UserPrincipal;
import br.com.cwi.crescer.weatherattack.service.desafio.ListarDesafiosService;
import br.com.cwi.crescer.weatherattack.service.desafio.RecusarDesafioService;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.runners.MockitoJUnitRunner;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.messaging.simp.SimpMessagingTemplate;
import org.springframework.security.core.GrantedAuthority;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import static org.mockito.Matchers.any;

@RunWith(MockitoJUnitRunner.class)
public class RecusarDesafioServiceTest {

    @InjectMocks
    private RecusarDesafioService tested;

    @Mock
    private SimpMessagingTemplate simpMessagingTemplate;

    @Mock
    private ListarDesafiosService listarDesafiosService;

    private Collection<? extends GrantedAuthority> authorities;

    @Test
    public void naoDeveRetornarErro(){
        UserPrincipal userPrincipal = new UserPrincipal(1L, "teste", "teste", "teste", "teste", authorities);
        UsuarioDtoResponse usuario = new UsuarioDtoResponse();
        usuario.setId(1L);
        usuario.setApelido("teste");
        UsuarioDtoResponse usuario1 = new UsuarioDtoResponse();
        usuario.setId(2L);
        usuario1.setApelido("teste");
        List<Desafio> lista = new ArrayList<>();
        Desafio desafio = new Desafio(usuario, usuario1);
        desafio.setId("1");
        desafio.getDesafiado().setId(1L);
        lista.add(desafio);
        Mockito.when(listarDesafiosService.getDesafios()).thenReturn(lista);
        Mockito.doNothing().when(simpMessagingTemplate).convertAndSend(any());

        String mensagem = tested.recusarDesafio("1",userPrincipal );
        Assert.assertEquals("DESAFIO recusado por: teste", mensagem);
    }
}
