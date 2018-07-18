package br.com.cwi.crescer.weatherattack.service.personagem;

import br.com.cwi.crescer.weatherattack.dominio.Personagem;
import br.com.cwi.crescer.weatherattack.repository.PersonagemDelegate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class AtualizarPersonagemService {

    @Autowired
    private BuscarPersonagemService buscarPersonagemPorIdUsuarioService;

    @Autowired
    private PersonagemDelegate repository;

    public Personagem atualizar(Personagem personagem){
        Personagem p = buscarPersonagemPorIdUsuarioService.buscarPersonagemPorId(personagem.getId());

        p.setVida(personagem.getVida());
        repository.save(p);
        return p;
    }

}
