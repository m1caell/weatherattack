package br.com.cwi.crescer.weatherattack.repository;

import br.com.cwi.crescer.weatherattack.dominio.Poder;
import org.springframework.data.repository.Repository;

import java.util.List;
import java.util.Optional;

public interface PoderDelegate extends Repository<Poder,Long> {

    Optional<Poder> findById(Long id);

    List<Poder> findAll();
}
