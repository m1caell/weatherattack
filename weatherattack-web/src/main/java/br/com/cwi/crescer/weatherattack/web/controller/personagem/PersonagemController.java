package br.com.cwi.crescer.weatherattack.web.controller.personagem;

import br.com.cwi.crescer.weatherattack.dominio.Personagem;
import br.com.cwi.crescer.weatherattack.service.personagem.BuscarPersonagemPorIdUsuarioService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/personagem")
public class PersonagemController {

    @Autowired
    private BuscarPersonagemPorIdUsuarioService buscarPersonagemPorIdUsuarioService;

    @GetMapping("/{idUsuario}")
    @ResponseStatus(HttpStatus.CREATED)
    public Personagem AdicionarUsuario(@PathVariable Long idUsuario){
        return buscarPersonagemPorIdUsuarioService.buscarPersonagemPorIdUsuario(idUsuario);
    }
}
