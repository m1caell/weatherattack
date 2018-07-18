package br.com.cwi.crescer.weatherattack.service.logCombate;

import br.com.cwi.crescer.weatherattack.dominio.LogCombate;
import br.com.cwi.crescer.weatherattack.repository.LogCombateDelegate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class BuscarLogCombateService {

    @Autowired
    private LogCombateDelegate repository;

    public List<LogCombate> buscarTodos(){
        return repository.findAll();
    }
}
