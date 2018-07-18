package br.com.cwi.crescer.weatherattack.service.poderes;

import br.com.cwi.crescer.weatherattack.configuration.Configurations;
import br.com.cwi.crescer.weatherattack.dominio.Poder;
import br.com.cwi.crescer.weatherattack.dto.request.LocalizacaoDtoRequest;
import br.com.cwi.crescer.weatherattack.exception.clima.LocalizaoInvalidaException;
import br.com.cwi.crescer.weatherattack.service.clima.mapearService.MapearCurrentWeatherParaPoderes;
import br.com.cwi.crescer.weatherattack.service.clima.mapearService.MapearCurrentWeatherToClimaDtoResponse;
import net.aksingh.owmjapis.api.APIException;
import net.aksingh.owmjapis.model.CurrentWeather;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class BuscarPoderesDaLocalizacaoService {

    @Autowired
    private Configurations configurations;

    @Autowired
    private MapearCurrentWeatherToClimaDtoResponse mapearCurrentWeatherToClimaDtoResponse;

    @Autowired
    private MapearCurrentWeatherParaPoderes mapearCurrentWeatherParaPoderes;

    public List<Poder> obterPoderesDaLocalizacao(LocalizacaoDtoRequest localizacaoDtoRequest){

        if (localizacaoDtoRequest.latitude == null || localizacaoDtoRequest.longitude == null){
            return new ArrayList<>();
        }

        double latitude = localizacaoDtoRequest.latitude.doubleValue();
        double longitude = localizacaoDtoRequest.longitude.doubleValue();
        CurrentWeather currentWeather;

        try {
            currentWeather = configurations.openWeatherMap().currentWeatherByCoords(latitude,longitude);
        } catch (APIException e) {
            throw new LocalizaoInvalidaException();
        }

        return mapearCurrentWeatherParaPoderes.mapear(currentWeather);
    }
}
