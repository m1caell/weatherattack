package br.com.cwi.crescer.weatherattack.web.controller.clima;

import br.com.cwi.crescer.weatherattack.dto.request.LocalizacaoDtoRequest;
import br.com.cwi.crescer.weatherattack.dto.response.ClimaDtoResponse;
import br.com.cwi.crescer.weatherattack.service.clima.ObterClimaService;
import br.com.cwi.crescer.weatherattack.service.poderes.BuscarPoderesDaLocalizacaoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/public/clima")
public class ClimaController {

    @Autowired
    private ObterClimaService obterClimaService;
    @Autowired
    private BuscarPoderesDaLocalizacaoService buscarPoderesDaLocalizacaoService;

    @PostMapping
    @ResponseStatus(HttpStatus.OK)
    public ClimaDtoResponse obterClima(@RequestBody LocalizacaoDtoRequest localizacaoDtoRequest){
        return obterClimaService.obterClima(localizacaoDtoRequest);
    }

}
