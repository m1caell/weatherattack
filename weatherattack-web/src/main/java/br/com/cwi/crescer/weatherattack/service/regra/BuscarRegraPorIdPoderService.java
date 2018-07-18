package br.com.cwi.crescer.weatherattack.service.regra;

import br.com.cwi.crescer.weatherattack.dominio.Regra;
import br.com.cwi.crescer.weatherattack.repository.RegraDelegate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class BuscarRegraPorIdPoderService {

    @Autowired
    private RegraDelegate repository;

    public List<Regra> buscarPorIdPoder(Long idPoder){
        return repository.findByPoderId(idPoder);
    }
}
