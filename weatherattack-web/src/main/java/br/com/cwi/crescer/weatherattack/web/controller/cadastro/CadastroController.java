package br.com.cwi.crescer.weatherattack.web.controller.cadastro;

import br.com.cwi.crescer.weatherattack.dto.request.UsuarioDtoRequest;
import br.com.cwi.crescer.weatherattack.dto.response.UsuarioDtoResponse;
import br.com.cwi.crescer.weatherattack.service.usuario.AdicionarUsuarioService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/public")
public class CadastroController {

    @Autowired
    private AdicionarUsuarioService adicionarUsuarioService;

    @PostMapping("/register")
    @ResponseStatus(HttpStatus.CREATED)
    public UsuarioDtoResponse AdicionarUsuario(@RequestBody UsuarioDtoRequest usuario){
        return adicionarUsuarioService.adicionarUsuario(usuario);
    }
}
