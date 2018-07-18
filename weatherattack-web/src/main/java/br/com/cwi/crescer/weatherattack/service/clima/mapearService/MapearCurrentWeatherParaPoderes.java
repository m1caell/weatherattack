package br.com.cwi.crescer.weatherattack.service.clima.mapearService;

import br.com.cwi.crescer.weatherattack.dominio.Poder;
import br.com.cwi.crescer.weatherattack.dominio.Regra;
import br.com.cwi.crescer.weatherattack.service.poderes.BuscarPoderesService;
import net.aksingh.owmjapis.model.CurrentWeather;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.LinkedList;
import java.util.List;
import java.util.Objects;

@Service
public class MapearCurrentWeatherParaPoderes extends MapearCurrentWeatherUtils {

    @Autowired
    private BuscarPoderesService buscarPoderesService;

    public List<Poder> mapear(CurrentWeather currentWeather){
        List<Poder> poderes = new LinkedList<>();

        for(Poder poder : buscarPoderesService.buscarPoderes()){
            boolean temPoder = false;
            for(Regra regra : poder.getRegras()){
                switch(regra.getCondicao().getNome()){
                    case NEVE:
                        poder = receberPoder(regra,super.getNeve(currentWeather).doubleValue());
                        temPoder = Objects.nonNull(poder);
                        break;
                    case CHUVA:
                        poder = receberPoder(regra,super.getChuva(currentWeather).doubleValue());
                        temPoder = Objects.nonNull(poder);
                        break;
                    case VENTO:
                        poder = receberPoder(regra,super.getVento(currentWeather).doubleValue());
                        temPoder = Objects.nonNull(poder);
                        break;
                    case TEMPESTADE:
                        poder = receberPoder(regra,super.getTempestade(currentWeather).doubleValue());
                        temPoder = Objects.nonNull(poder);
                        break;
                    case TEMPERATURA:
                        poder = receberPoder(regra,super.getTemperatura(currentWeather).doubleValue());
                        temPoder = Objects.nonNull(poder);
                        break;
                }
            }
            if(temPoder && !poderes.contains(poder)){
                poderes.add(poder);
            }
        }

        return poderes;
    }


    private Poder receberPoder(Regra regra, Double valor){
        switch (regra.getParametro()){
            case MAIOR_OU_IGUAL_A:
                if(valor >= regra.getValor().doubleValue()){
                    return regra.getPoder();
                }
                break;
            case MENOR_OU_IGUAL_A:
                if(valor <= regra.getValor().doubleValue()){
                    return regra.getPoder();
                }
                break;

            case MAIOR_QUE:
                if(valor > regra.getValor().doubleValue()){
                    return regra.getPoder();
                }
                break;
            case MENOR_QUE:
                if(valor < regra.getValor().doubleValue()){
                    return regra.getPoder();
                }
                break;
        }

        return null;
    }

}
