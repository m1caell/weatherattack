package br.com.cwi.crescer.weatherattack.poderesTests;

import br.com.cwi.crescer.weatherattack.repository.PoderDelegate;
import br.com.cwi.crescer.weatherattack.service.poderes.BuscarPoderesService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.runners.MockitoJUnitRunner;

@RunWith(MockitoJUnitRunner.class)
public class BuscarPoderesServiceTest {

    @InjectMocks
    private BuscarPoderesService tested;

    @Mock
    private PoderDelegate repository;

    @Test
    public void devePassarPorFindAll(){
        tested.buscarPoderes();
        Mockito.verify(repository, Mockito.times(1)).findAll();
    }
}
