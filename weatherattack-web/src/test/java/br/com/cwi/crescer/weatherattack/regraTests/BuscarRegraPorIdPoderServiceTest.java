package br.com.cwi.crescer.weatherattack.regraTests;

import br.com.cwi.crescer.weatherattack.repository.RegraDelegate;

import br.com.cwi.crescer.weatherattack.service.regra.BuscarRegraPorIdPoderService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.runners.MockitoJUnitRunner;

@RunWith(MockitoJUnitRunner.class)
public class BuscarRegraPorIdPoderServiceTest {

    @InjectMocks
    private BuscarRegraPorIdPoderService tested;

    @Mock
    private RegraDelegate repository;

    @Test
    public void devePassarPorFindAll(){
        tested.buscarPorIdPoder(1L);
        Mockito.verify(repository, Mockito.times(1)).findByPoderId(1L);
    }

}
