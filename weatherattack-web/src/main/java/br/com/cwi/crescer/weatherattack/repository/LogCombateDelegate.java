package br.com.cwi.crescer.weatherattack.repository;

import br.com.cwi.crescer.weatherattack.dominio.LogCombate;
import org.springframework.data.repository.Repository;

import java.util.List;

public interface LogCombateDelegate extends Repository<LogCombate, Long> {

    LogCombate save(LogCombate logCombate);

    List<LogCombate> findAll();

    List<LogCombate> findTop4ByDesafiado_IdOrDesafiante_IdOrderByDataFimDesc(Long idDesafiado, Long idDesafiante);
}
