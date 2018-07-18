package br.com.cwi.crescer.weatherattack.usuarioTests;

import br.com.cwi.crescer.weatherattack.dominio.Usuario;
import br.com.cwi.crescer.weatherattack.dto.request.UsuarioDtoRequest;
import br.com.cwi.crescer.weatherattack.exception.usuario.UsuarioNaoEncontradoException;
import br.com.cwi.crescer.weatherattack.repository.UsuarioDelegate;
import br.com.cwi.crescer.weatherattack.service.criptografia.CriptografiaService;
import br.com.cwi.crescer.weatherattack.service.personagem.CriarNovoPersonagemService;
import br.com.cwi.crescer.weatherattack.service.usuario.AdicionarUsuarioService;
import br.com.cwi.crescer.weatherattack.service.usuario.BuscarUsuarioPorEmailService;
import br.com.cwi.crescer.weatherattack.service.usuario.BuscarUsuarioResponsePorIdService;
import br.com.cwi.crescer.weatherattack.service.usuario.ValidarUsuarioService;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.*;
import org.mockito.runners.MockitoJUnitRunner;

import static org.mockito.Matchers.any;

@RunWith(MockitoJUnitRunner.class)
public class AdicionarUsuarioServiceTest {

    @InjectMocks
    private AdicionarUsuarioService tested;

    @Mock
    private UsuarioDelegate repository;

    @Mock
    private BuscarUsuarioPorEmailService buscarUsuarioPorEmailService;

    @Mock
    private ValidarUsuarioService validarUsuarioService;

    @Mock
    private CriptografiaService criptografiaService;

    @Mock
    private BuscarUsuarioResponsePorIdService buscarUsuarioResponsePorIdService;

    @Mock
    private CriarNovoPersonagemService criarNovoPersonagemService;

    @Captor
    ArgumentCaptor<Usuario> captador;


    @Test
    public void naoDeveRetornarErro(){
        UsuarioDtoRequest usuarioDtoRequest = new UsuarioDtoRequest();
        usuarioDtoRequest.setSenha("123");
        usuarioDtoRequest.setApelido("teste");
        usuarioDtoRequest.setEmail("teste");
        usuarioDtoRequest.setNome("teste");

        Mockito.when(buscarUsuarioPorEmailService.buscarUsuarioPorEmail(any())).thenThrow(new UsuarioNaoEncontradoException());
        Mockito.doNothing().when(repository).save(captador.capture());
        tested.adicionarUsuario(usuarioDtoRequest);

        Usuario capturado = captador.getValue();
        Assert.assertEquals(usuarioDtoRequest.getApelido(), capturado.getApelido());
        Assert.assertEquals(usuarioDtoRequest.getEmail(), capturado.getEmail());
        Assert.assertEquals(usuarioDtoRequest.getNome(), capturado.getNome());
    }


}
