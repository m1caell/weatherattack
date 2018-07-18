package br.com.cwi.crescer.weatherattack.logCombateTests;

import br.com.cwi.crescer.weatherattack.dominio.LogCombate;
import br.com.cwi.crescer.weatherattack.repository.LogCombateDelegate;
import br.com.cwi.crescer.weatherattack.service.logCombate.AdicionarLogCombateService;
import br.com.cwi.crescer.weatherattack.service.logCombate.BuscarLogCombateService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.runners.MockitoJUnitRunner;

import static org.mockito.Matchers.any;

@RunWith(MockitoJUnitRunner.class)
public class AdicionarLogCombateServiceTest {

    @InjectMocks
    private AdicionarLogCombateService tested;

    @Mock
    private LogCombateDelegate repository;

    @Test
    public void devePassarPorSave(){
        tested.salvar(new LogCombate());
        Mockito.verify(repository, Mockito.times(1)).save(any());
    }
}
