package br.com.cwi.crescer.weatherattack.service.logCombate;

import br.com.cwi.crescer.weatherattack.dominio.LogCombate;
import br.com.cwi.crescer.weatherattack.repository.LogCombateDelegate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class AdicionarLogCombateService {


    @Autowired
    private LogCombateDelegate repository;

    public LogCombate salvar(LogCombate logCombate){
        repository.save(logCombate);
        return logCombate;
    }
}
