package br.com.cwi.crescer.weatherattack.service.personagem;

import br.com.cwi.crescer.weatherattack.dominio.Personagem;
import br.com.cwi.crescer.weatherattack.repository.PersonagemDelegate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class AlterarPersonagemService {

    @Autowired
    private PersonagemDelegate repository;

    public void alterar(Personagem personagem){
        repository.save(personagem);
    }
}
