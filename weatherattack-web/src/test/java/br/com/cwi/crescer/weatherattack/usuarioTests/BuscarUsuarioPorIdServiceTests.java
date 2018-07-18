package br.com.cwi.crescer.weatherattack.usuarioTests;

import br.com.cwi.crescer.weatherattack.dominio.Usuario;
import br.com.cwi.crescer.weatherattack.exception.usuario.UsuarioNaoEncontradoException;
import br.com.cwi.crescer.weatherattack.repository.UsuarioDelegate;
import br.com.cwi.crescer.weatherattack.service.usuario.BuscarUsuarioPorIdService;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.runners.MockitoJUnitRunner;

import java.util.Optional;

@RunWith(MockitoJUnitRunner.class)
public class BuscarUsuarioPorIdServiceTests {

    @InjectMocks
    private BuscarUsuarioPorIdService tested;

    @Mock
    private UsuarioDelegate repository;

    @Test
    public void deveRetornarUsuarioExistente() {

        // Cenário
        Long id = 1L;
        Usuario usuario = new Usuario();
        usuario.setId(id);

        // Cenário - Mocks
        Mockito.when(repository.findById(id)).thenReturn(Optional.of(usuario));

        // Método que deve ser testado
        Usuario usuarioRetornado = tested.buscarPorId(id);

        // Verificações
        Assert.assertNotNull(usuarioRetornado);
        Assert.assertEquals(id, usuarioRetornado.getId());
    }

    @Test(expected = UsuarioNaoEncontradoException.class)
    public void deveLancarExceptionQuandoIdNulo() {
        tested.buscarPorId(null);
    }

    @Test(expected = UsuarioNaoEncontradoException.class)
    public void deveLancarExceptionQuandoUsuarioInexistente() {

        // Cenário
        Long id = 1L;

        // Cenário - mocks
        Mockito.when(repository.findById(id)).thenReturn(Optional.empty());

        // Método que deve ser testado
        Usuario usuarioRetornado = tested.buscarPorId(id);

    }
}
