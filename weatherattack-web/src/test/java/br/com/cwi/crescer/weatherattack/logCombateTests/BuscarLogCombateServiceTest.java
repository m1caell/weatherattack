package br.com.cwi.crescer.weatherattack.logCombateTests;

import br.com.cwi.crescer.weatherattack.repository.LogCombateDelegate;
import br.com.cwi.crescer.weatherattack.service.logCombate.BuscarLogCombateService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.runners.MockitoJUnitRunner;

@RunWith(MockitoJUnitRunner.class)
public class BuscarLogCombateServiceTest {

    @InjectMocks
    private BuscarLogCombateService tested;

    @Mock
    private LogCombateDelegate repository;

    @Test
    public void devePassarPorFindAll(){
        tested.buscarTodos();
        Mockito.verify(repository, Mockito.times(1)).findAll();
    }
}
