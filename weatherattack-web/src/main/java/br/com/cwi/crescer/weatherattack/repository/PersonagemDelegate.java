package br.com.cwi.crescer.weatherattack.repository;

import br.com.cwi.crescer.weatherattack.dominio.Personagem;
import br.com.cwi.crescer.weatherattack.dto.response.PersonagemDtoResponse;
import org.springframework.data.repository.Repository;

import java.util.List;
import java.util.Optional;

public interface PersonagemDelegate extends Repository<Personagem,Long> {

    List<Personagem> findAll();

    Optional<Personagem> findById(Long id);
    Optional<Personagem> findByUsuario_Id(Long idUsuario);
    void save(Personagem personagem);
}
