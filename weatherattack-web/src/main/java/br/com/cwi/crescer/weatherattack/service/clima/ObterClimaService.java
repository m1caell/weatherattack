package br.com.cwi.crescer.weatherattack.service.clima;

import br.com.cwi.crescer.weatherattack.configuration.Configurations;
import br.com.cwi.crescer.weatherattack.dto.request.LocalizacaoDtoRequest;
import br.com.cwi.crescer.weatherattack.dto.response.ClimaDtoResponse;
import br.com.cwi.crescer.weatherattack.exception.clima.LocalizaoInvalidaException;
import br.com.cwi.crescer.weatherattack.service.clima.mapearService.MapearCurrentWeatherToClimaDtoResponse;
import net.aksingh.owmjapis.api.APIException;
import net.aksingh.owmjapis.model.CurrentWeather;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


@Service
public class ObterClimaService {

    @Autowired
    private Configurations configurations;

    @Autowired
    private MapearCurrentWeatherToClimaDtoResponse mapearCurrentWeatherToClimaDtoResponse;

    //Chave da api de clima

    public ClimaDtoResponse obterClima(LocalizacaoDtoRequest localizacaoDtoRequest){

        if (localizacaoDtoRequest.latitude == null || localizacaoDtoRequest.longitude == null){
            throw new LocalizaoInvalidaException();
        }

        double latitude = localizacaoDtoRequest.latitude.doubleValue();
        double longitude = localizacaoDtoRequest.longitude.doubleValue();
        CurrentWeather currentWeather;

        try {
            currentWeather = configurations.openWeatherMap().currentWeatherByCoords(latitude,longitude);
        } catch (APIException e) {
            throw new LocalizaoInvalidaException();
        }

        return mapearCurrentWeatherToClimaDtoResponse.mapear(currentWeather);
    }
}
