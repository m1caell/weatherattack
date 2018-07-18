package br.com.cwi.crescer.weatherattack.web.controller.poderes;

import br.com.cwi.crescer.weatherattack.dominio.Poder;
import br.com.cwi.crescer.weatherattack.dto.request.LocalizacaoDtoRequest;
import br.com.cwi.crescer.weatherattack.service.poderes.BuscarPoderesDaLocalizacaoService;
import br.com.cwi.crescer.weatherattack.service.poderes.BuscarPoderesService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/public/poderes")
public class PoderesController {

    @Autowired
    private BuscarPoderesService buscarPoderesService;

    @Autowired
    private BuscarPoderesDaLocalizacaoService buscarPoderesDaLocalizacaoService;

    @GetMapping
    @ResponseStatus(HttpStatus.OK)
    public List<Poder> obterTodosPoderes(){
        return buscarPoderesService.buscarPoderes();
    }

    @PostMapping
    public List<Poder> obterPoderesDaLocalizacao(@RequestBody LocalizacaoDtoRequest localizacaoDtoRequest){
        return buscarPoderesDaLocalizacaoService.obterPoderesDaLocalizacao(localizacaoDtoRequest);
    }
}
