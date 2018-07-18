package br.com.cwi.crescer.weatherattack.loginTests;

import br.com.cwi.crescer.weatherattack.dto.request.LoginDtoRequest;
import br.com.cwi.crescer.weatherattack.exception.login.LoginInvalidoException;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.runners.MockitoJUnitRunner;
import br.com.cwi.crescer.weatherattack.service.login.ValidarLoginService;

@RunWith(MockitoJUnitRunner.class)
public class ValidarLoginServiceTest {

    @InjectMocks
    ValidarLoginService tested;

    @Test(expected = LoginInvalidoException.class)
    public void deveLancarExceptionQuandoRequestNulo() {
        tested.validarUsuario(null);
    }

    @Test(expected = LoginInvalidoException.class)
    public void deveLancarExceptionQuandoEmailNulo() {
        LoginDtoRequest loginDtoRequest = new LoginDtoRequest(null, "123");
        tested.validarUsuario(loginDtoRequest);
    }

    @Test(expected = LoginInvalidoException.class)
    public void deveLancarExceptionQuandoSenhaNulo() {
        LoginDtoRequest loginDtoRequest = new LoginDtoRequest("teste", null);
        tested.validarUsuario(loginDtoRequest);
    }

    @Test
    public void naoDeveLancarExceptionQuandoLoginCorreto() {
        LoginDtoRequest loginDtoRequest = new LoginDtoRequest("teste", "teste");
        tested.validarUsuario(loginDtoRequest);
    }
}