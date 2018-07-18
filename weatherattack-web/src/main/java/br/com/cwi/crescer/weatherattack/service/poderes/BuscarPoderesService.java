package br.com.cwi.crescer.weatherattack.service.poderes;

import br.com.cwi.crescer.weatherattack.dominio.Poder;
import br.com.cwi.crescer.weatherattack.repository.PoderDelegate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class BuscarPoderesService {

    @Autowired
    private PoderDelegate repository;

    public List<Poder> buscarPoderes(){
        return repository.findAll();
    }
}
