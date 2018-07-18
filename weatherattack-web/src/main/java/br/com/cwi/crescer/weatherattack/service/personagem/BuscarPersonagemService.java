package br.com.cwi.crescer.weatherattack.service.personagem;

import br.com.cwi.crescer.weatherattack.dominio.Personagem;
import br.com.cwi.crescer.weatherattack.dto.response.PersonagemDtoResponse;
import br.com.cwi.crescer.weatherattack.exception.personagem.PersonagemNaoEncontradoException;
import br.com.cwi.crescer.weatherattack.repository.PersonagemDelegate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class BuscarPersonagemService {

    @Autowired
    private PersonagemDelegate repository;

    public Personagem buscarPersonagemPorId(Long id){
        return repository.findById(id).orElseThrow(() -> new PersonagemNaoEncontradoException());
    }
}
