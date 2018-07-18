package br.com.cwi.crescer.weatherattack.service.clima.mapearService;

import net.aksingh.owmjapis.model.CurrentWeather;

import java.math.BigDecimal;

public class MapearCurrentWeatherUtils {

    protected String getNomeDaCidade(CurrentWeather currentWeather){
        return currentWeather.hasCityName() ?
                currentWeather.getCityName() :
                "Cidade não encontrada";
    }

    protected BigDecimal getTemperatura(CurrentWeather currentWeather){
        return new BigDecimal(currentWeather.component8().getTemp() - 273.15);
    }

    protected BigDecimal getVento(CurrentWeather currentWeather){
        return currentWeather.hasWindData() ?
                new BigDecimal(currentWeather.getWindData().getSpeed()) :
                new BigDecimal(0);
    }

    protected BigDecimal getChuva(CurrentWeather currentWeather){
        return currentWeather.hasRainData() ?
                new BigDecimal(currentWeather.getRainData().getPrecipVol3h()) :
                new BigDecimal(0);
    }

    protected BigDecimal getNeve(CurrentWeather currentWeather){
        return currentWeather.hasSnowData() ?
                new BigDecimal(currentWeather.getSnowData().getSnowVol3h()) :
                new BigDecimal(0);
    }

    protected BigDecimal getTempestade(CurrentWeather currentWeather){
        Integer codigoDoClima = 0;
        if(currentWeather.component11().hasId()){
            codigoDoClima = currentWeather.getSystemData().getId();
            codigoDoClima = codigoDoClima != null ? Math.abs(codigoDoClima/100) : 0;
        }
        return new BigDecimal(codigoDoClima == 3 ? 1 : 0);
    }
}
