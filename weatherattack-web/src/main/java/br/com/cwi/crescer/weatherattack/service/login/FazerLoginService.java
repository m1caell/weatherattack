package br.com.cwi.crescer.weatherattack.service.login;

import br.com.cwi.crescer.weatherattack.dominio.Usuario;
import br.com.cwi.crescer.weatherattack.dto.request.LoginDtoRequest;
import br.com.cwi.crescer.weatherattack.dto.response.LoginDtoResponse;
import br.com.cwi.crescer.weatherattack.security.AuthenticationService;
import br.com.cwi.crescer.weatherattack.service.criptografia.CriptografiaService;
import br.com.cwi.crescer.weatherattack.service.usuario.BuscarUsuarioPorEmailService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class FazerLoginService {

    @Autowired
    private AuthenticationService authenticationService;

    @Autowired
    private BuscarUsuarioPorEmailService buscarUsuarioPorEmailService;

    @Autowired
    private CriptografiaService criptografiaService;

    @Autowired
    private ValidarLoginService validarLoginService;

    public LoginDtoResponse fazerLogin(LoginDtoRequest request){

        validarLoginService.validarUsuario(request);

        String email = request.getEmail();
        String senhaCanditada = request.getSenha();

        Usuario u = buscarUsuarioPorEmailService.buscarUsuarioPorEmail(email);
        String senhaHash = u.getSenha();

        criptografiaService.CompararSenha(senhaCanditada,senhaHash);

        String token = authenticationService.authenticate(email, senhaHash);
        return new LoginDtoResponse(token);

    }
}
