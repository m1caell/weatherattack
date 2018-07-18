package br.com.cwi.crescer.weatherattack.web.controller.logCombate;

import br.com.cwi.crescer.weatherattack.dominio.LogCombate;
import br.com.cwi.crescer.weatherattack.dto.response.LogCombateDtoResponse;
import br.com.cwi.crescer.weatherattack.security.UserPrincipal;
import br.com.cwi.crescer.weatherattack.service.logCombate.BuscarLogCombatePorIdUsuarioService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/logcombate")
public class LogCombateController {

    @Autowired
    private BuscarLogCombatePorIdUsuarioService buscarLogCombatePorIdUsuarioService;


    @GetMapping
    public List<LogCombateDtoResponse> buscarLogCombatePorIdUsuario(@AuthenticationPrincipal UserPrincipal usuarioLogado){
        return buscarLogCombatePorIdUsuarioService.buscar(usuarioLogado.getId());
    }
}
