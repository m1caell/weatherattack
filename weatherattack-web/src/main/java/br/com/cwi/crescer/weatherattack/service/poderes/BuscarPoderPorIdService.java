package br.com.cwi.crescer.weatherattack.service.poderes;

import br.com.cwi.crescer.weatherattack.dominio.Poder;
import br.com.cwi.crescer.weatherattack.exception.poder.PoderNaoEncontradoException;
import br.com.cwi.crescer.weatherattack.repository.PoderDelegate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class BuscarPoderPorIdService {

    @Autowired
    private PoderDelegate repository;

    public Poder buscarPoderPorId(Long id){
        return repository.findById(id).orElseThrow(() -> new PoderNaoEncontradoException());
    }
}
