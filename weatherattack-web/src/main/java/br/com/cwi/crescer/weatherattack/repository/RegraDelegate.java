package br.com.cwi.crescer.weatherattack.repository;

import br.com.cwi.crescer.weatherattack.dominio.Regra;
import org.springframework.data.repository.Repository;

import java.util.List;

public interface RegraDelegate extends Repository<Regra,Long> {

    List<Regra> findAll();

    List<Regra> findByPoderId(Long idPoder);
}
