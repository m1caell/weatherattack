package br.com.cwi.crescer.weatherattack.service.clima.mapearService;

import br.com.cwi.crescer.weatherattack.dto.response.ClimaDtoResponse;
import net.aksingh.owmjapis.model.CurrentWeather;
import org.springframework.stereotype.Service;

@Service
public class MapearCurrentWeatherToClimaDtoResponse extends MapearCurrentWeatherUtils {


    public ClimaDtoResponse mapear(CurrentWeather currentWeather){

        ClimaDtoResponse climaDto = new ClimaDtoResponse();
        //Converte para Celcius
        climaDto.setTemperatura(super.getTemperatura(currentWeather));
        climaDto.setVento(super.getVento(currentWeather));
        climaDto.setChuva(super.getChuva(currentWeather));
        climaDto.setNeve(super.getNeve(currentWeather));
        climaDto.setTempestade(super.getTempestade(currentWeather));
        climaDto.setNomeDaCidade(super.getNomeDaCidade(currentWeather));
        return climaDto;
    }
}
