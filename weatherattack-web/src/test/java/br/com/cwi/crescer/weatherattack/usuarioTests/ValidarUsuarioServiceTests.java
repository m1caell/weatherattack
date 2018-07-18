package br.com.cwi.crescer.weatherattack.usuarioTests;


import br.com.cwi.crescer.weatherattack.dto.request.UsuarioDtoRequest;
import br.com.cwi.crescer.weatherattack.exception.usuario.UsuarioInvalidoException;
import br.com.cwi.crescer.weatherattack.service.usuario.ValidarUsuarioService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.runners.MockitoJUnitRunner;

@RunWith(MockitoJUnitRunner.class)
public class ValidarUsuarioServiceTests {

    @InjectMocks
    private ValidarUsuarioService tested;

    @Test
    public void UsuarioComCampoValidoNaoDeveRetornarException(){
        UsuarioDtoRequest usuario = new UsuarioDtoRequest();
        usuario.setEmail("");
        usuario.setApelido("");
        usuario.setNome("");
        usuario.setSenha("");
        tested.validarUsuario(usuario);
    }

    @Test(expected = UsuarioInvalidoException.class)
    public void UsuarioNullDeveRetornarException(){
        tested.validarUsuario(null);
    }

    @Test(expected = UsuarioInvalidoException.class)
    public void UsuarioComNomeNullDeveRetornarException(){
        UsuarioDtoRequest usuario = new UsuarioDtoRequest();
        usuario.setEmail("");
        usuario.setApelido("");
        usuario.setNome(null);
        usuario.setSenha("");
        tested.validarUsuario(usuario);
    }

    @Test(expected = UsuarioInvalidoException.class)
    public void UsuarioComEmailNullDeveRetornarException(){
        UsuarioDtoRequest usuario = new UsuarioDtoRequest();
        usuario.setEmail(null);
        usuario.setApelido("");
        usuario.setNome("");
        usuario.setSenha("");
        tested.validarUsuario(usuario);
    }

    @Test(expected = UsuarioInvalidoException.class)
    public void UsuarioComApelidoNullDeveRetornarException(){
        UsuarioDtoRequest usuario = new UsuarioDtoRequest();
        usuario.setEmail("");
        usuario.setApelido(null);
        usuario.setNome("");
        usuario.setSenha("");
        tested.validarUsuario(usuario);
    }

    @Test(expected = UsuarioInvalidoException.class)
    public void UsuarioComSenhaNullDeveRetornarException(){
        UsuarioDtoRequest usuario = new UsuarioDtoRequest();
        usuario.setEmail("");
        usuario.setApelido("");
        usuario.setNome("");
        usuario.setSenha(null);
        tested.validarUsuario(usuario);
    }


}
