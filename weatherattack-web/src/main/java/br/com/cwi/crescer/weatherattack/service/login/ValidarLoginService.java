package br.com.cwi.crescer.weatherattack.service.login;

import br.com.cwi.crescer.weatherattack.dto.request.LoginDtoRequest;
import br.com.cwi.crescer.weatherattack.exception.login.LoginInvalidoException;
import org.springframework.stereotype.Service;

import java.util.Objects;

@Service
public class ValidarLoginService {

    public void validarUsuario(LoginDtoRequest request){
        if(Objects.isNull(request)){
            throw new LoginInvalidoException("Login inválido.");
        }
        if(Objects.isNull(request.getEmail())){
            throw new LoginInvalidoException("Email inválido.");
        }
        if(Objects.isNull(request.getSenha())){
            throw new LoginInvalidoException("Senha inválida.");
        }
    }
}
