package br.com.cwi.crescer.weatherattack.poderesTests;

import br.com.cwi.crescer.weatherattack.dominio.Poder;
import br.com.cwi.crescer.weatherattack.repository.PoderDelegate;
import br.com.cwi.crescer.weatherattack.service.poderes.BuscarPoderPorIdService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.runners.MockitoJUnitRunner;

import java.util.Optional;

import static org.mockito.Matchers.any;

@RunWith(MockitoJUnitRunner.class)
public class BuscarPoderPorIdServiceTest {

    @InjectMocks
    private BuscarPoderPorIdService tested;

    @Mock
    private PoderDelegate repository;

    @Test
    public void devePassarPorFindById(){
        Mockito.when(repository.findById(1L)).thenReturn(Optional.of(new Poder()));
        tested.buscarPoderPorId(1L);
        Mockito.verify(repository, Mockito.times(1)).findById(1L);
    }
}
