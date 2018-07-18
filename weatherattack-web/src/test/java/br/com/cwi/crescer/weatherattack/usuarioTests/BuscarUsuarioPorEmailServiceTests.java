package br.com.cwi.crescer.weatherattack.usuarioTests;

import br.com.cwi.crescer.weatherattack.dominio.Usuario;
import br.com.cwi.crescer.weatherattack.exception.usuario.EmailInvalidoException;
import br.com.cwi.crescer.weatherattack.exception.usuario.UsuarioNaoEncontradoException;
import br.com.cwi.crescer.weatherattack.repository.UsuarioDelegate;
import br.com.cwi.crescer.weatherattack.service.usuario.BuscarUsuarioPorEmailService;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.runners.MockitoJUnitRunner;

import java.util.Optional;

@RunWith(MockitoJUnitRunner.class)
public class BuscarUsuarioPorEmailServiceTests {

    @InjectMocks
    private BuscarUsuarioPorEmailService tested;

    @Mock
    private UsuarioDelegate repository;

    @Test
    public void deveRetornarUsuarioExistente() {

        // Cenário
        String email = "email";
        Usuario usuario = new Usuario();
        usuario.setEmail(email);

        // Cenário - Mocks
        Mockito.when(repository.findByEmail(email)).thenReturn(Optional.of(usuario));

        // Método que deve ser testado
        Usuario usuarioRetornado = tested.buscarUsuarioPorEmail(email);

        // Verificações
        Assert.assertNotNull(usuarioRetornado);
        Assert.assertEquals(email, usuarioRetornado.getEmail());
    }

    @Test(expected = EmailInvalidoException.class)
    public void deveLancarExceptionQuandoIdNulo() {
        tested.buscarUsuarioPorEmail(null);
    }

    @Test(expected = UsuarioNaoEncontradoException.class)
    public void deveLancarExceptionQuandoUsuarioInexistente() {

        // Cenário
        String email = "email";
        Usuario usuario = new Usuario();
        usuario.setEmail(email);

        // Cenário - Mocks
        Mockito.when(repository.findByEmail(email)).thenReturn(Optional.empty());

        // Método que deve ser testado
        Usuario clienteRetornado = tested.buscarUsuarioPorEmail(email);

    }
}
