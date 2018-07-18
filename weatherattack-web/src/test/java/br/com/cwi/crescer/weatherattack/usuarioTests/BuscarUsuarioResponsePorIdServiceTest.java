package br.com.cwi.crescer.weatherattack.usuarioTests;

import br.com.cwi.crescer.weatherattack.dominio.Usuario;
import br.com.cwi.crescer.weatherattack.exception.usuario.UsuarioNaoEncontradoException;
import br.com.cwi.crescer.weatherattack.repository.UsuarioDelegate;
import br.com.cwi.crescer.weatherattack.service.usuario.BuscarUsuarioResponsePorIdService;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.runners.MockitoJUnitRunner;

import java.util.Optional;

@RunWith(MockitoJUnitRunner.class)
public class BuscarUsuarioResponsePorIdServiceTest {

    @InjectMocks
    private BuscarUsuarioResponsePorIdService tested;

    @Mock
    private UsuarioDelegate repository;

    @Test
    public void deveRetornarUsuarioExistente() {
        Long id = 1L;
        Usuario usuario = new Usuario();
        usuario.setId(id);
        usuario.setApelido("t");
        usuario.setEmail("t");
        usuario.setSenha("t");
        usuario.setNome("T");

        Mockito.when(repository.queryById(id)).thenReturn(usuario);

        tested.buscarPorId(id);

        Mockito.verify(repository, Mockito.times(1)).queryById(id);
    }

    @Test(expected = UsuarioNaoEncontradoException.class)
    public void deveLancarExceptionQuandoIdNulo() {
        tested.buscarPorId(null);
    }

}
