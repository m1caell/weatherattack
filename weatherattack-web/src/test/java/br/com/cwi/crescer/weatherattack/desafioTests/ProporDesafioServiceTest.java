package br.com.cwi.crescer.weatherattack.desafioTests;

import br.com.cwi.crescer.weatherattack.dominio.Desafio;
import br.com.cwi.crescer.weatherattack.dominio.Usuario;
import br.com.cwi.crescer.weatherattack.dto.request.DesafioDto;
import br.com.cwi.crescer.weatherattack.dto.response.UsuarioDtoResponse;
import br.com.cwi.crescer.weatherattack.dto.websocket.mensagem.MensagemInicioCombate;
import br.com.cwi.crescer.weatherattack.dto.websocket.mensagem.MensagemSolicitacaoDesafio;
import br.com.cwi.crescer.weatherattack.exception.desafio.DesafioInvalidoException;
import br.com.cwi.crescer.weatherattack.repository.UsuarioDelegate;
import br.com.cwi.crescer.weatherattack.security.UserPrincipal;
import br.com.cwi.crescer.weatherattack.service.desafio.DesafiarUsuarioService;
import br.com.cwi.crescer.weatherattack.service.desafio.ProporDesafioService;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.runners.MockitoJUnitRunner;
import org.springframework.messaging.simp.SimpMessagingTemplate;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static org.mockito.Matchers.any;

@RunWith(MockitoJUnitRunner.class)
public class ProporDesafioServiceTest {

    @InjectMocks
    private ProporDesafioService tested;

    @Mock
    private SimpMessagingTemplate simpMessagingTemplate;

    @Mock
    private DesafiarUsuarioService desafiarUsuarioService;

    @Mock
    private UsuarioDelegate repository;

    @Test
    public void naoDeveRetornarErro(){
        DesafioDto desafioDto = new DesafioDto();
        desafioDto.setIdDesafiado(1L);
        desafioDto.setIdDesafiante(2L);

        Usuario usuario = new Usuario();
        usuario.setId(1L);
        usuario.setApelido("teste");
        UsuarioDtoResponse usuarioDto = new UsuarioDtoResponse();
        usuarioDto.setId(1L);
        usuarioDto.setApelido("teste");

        Usuario usuario1 = new Usuario();
        usuario.setId(2L);
        usuario1.setApelido("teste");
        UsuarioDtoResponse usuarioDto1 = new UsuarioDtoResponse();
        usuarioDto1.setId(2L);
        usuarioDto1.setApelido("teste");

        Desafio desafio = new Desafio(usuarioDto, usuarioDto1);
        desafio.getDesafiante().setApelido("teste");
        Mockito.when(desafiarUsuarioService.desafiarUsuario(any(), any())).thenReturn(desafio);
        Mockito.doNothing().when(simpMessagingTemplate).convertAndSend(any());
        Mockito.when(repository.findById(1L)).thenReturn(Optional.of(usuario));
        Mockito.when(repository.findById(2L)).thenReturn(Optional.of(usuario1));

        MensagemSolicitacaoDesafio mensagem = tested.propor(desafioDto);
        Assert.assertEquals("Solicitação de desafio enviado para teste", mensagem.getMensagem());
    }

    @Test(expected = DesafioInvalidoException.class)
    public void deveMostrarErroCasoODesafianteForOMesmoQueODesafiado(){
        DesafioDto desafioDto = new DesafioDto();
        desafioDto.setIdDesafiado(1L);
        desafioDto.setIdDesafiante(1L);

        tested.propor(desafioDto);
    }
}
