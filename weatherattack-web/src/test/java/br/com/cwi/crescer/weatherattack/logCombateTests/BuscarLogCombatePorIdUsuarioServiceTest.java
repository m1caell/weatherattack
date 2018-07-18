package br.com.cwi.crescer.weatherattack.logCombateTests;

import br.com.cwi.crescer.weatherattack.repository.LogCombateDelegate;
import br.com.cwi.crescer.weatherattack.service.logCombate.BuscarLogCombatePorIdUsuarioService;
import br.com.cwi.crescer.weatherattack.service.logCombate.BuscarLogCombateService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.runners.MockitoJUnitRunner;

@RunWith(MockitoJUnitRunner.class)
public class BuscarLogCombatePorIdUsuarioServiceTest {

    @InjectMocks
    private BuscarLogCombatePorIdUsuarioService tested;

    @Mock
    private LogCombateDelegate repository;

    @Test
    public void devePassarPorFindTop4ByDesafiado_IdOrDesafiante_IdOrderByDataFimDesc(){
        tested.buscar(1L);
        Mockito.verify(repository, Mockito.times(1)).findTop4ByDesafiado_IdOrDesafiante_IdOrderByDataFimDesc(1L, 1L);
    }
}
