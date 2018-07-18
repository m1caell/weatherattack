package br.com.cwi.crescer.weatherattack.loginTests;

import br.com.cwi.crescer.weatherattack.dominio.Usuario;
import br.com.cwi.crescer.weatherattack.dto.request.LoginDtoRequest;
import br.com.cwi.crescer.weatherattack.security.AuthenticationService;
import br.com.cwi.crescer.weatherattack.service.criptografia.CriptografiaService;
import br.com.cwi.crescer.weatherattack.service.login.FazerLoginService;
import br.com.cwi.crescer.weatherattack.service.login.ValidarLoginService;
import br.com.cwi.crescer.weatherattack.service.usuario.BuscarUsuarioPorEmailService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.runners.MockitoJUnitRunner;

@RunWith(MockitoJUnitRunner.class)
public class FazerLoginServiceTest {

    @InjectMocks
    FazerLoginService tested;

    @Mock
    AuthenticationService authenticationService;

    @Mock
    BuscarUsuarioPorEmailService buscarUsuarioPorEmailService;

    @Mock
    private CriptografiaService criptografiaService;

    @Mock
    private ValidarLoginService validarLoginService;

    @Test
    public void naoDeveLancarExceptionQuandoSenhasIguais() {
        LoginDtoRequest loginDtoRequest = new LoginDtoRequest("teste", "teste");
        Usuario usuario = new Usuario();
        usuario.setSenha("teste");
        Mockito.when(buscarUsuarioPorEmailService.buscarUsuarioPorEmail("teste")).thenReturn(usuario);
        Mockito.when(criptografiaService.CompararSenha("teste", "teste")).thenReturn(true);
        tested.fazerLogin(loginDtoRequest);
    }
}