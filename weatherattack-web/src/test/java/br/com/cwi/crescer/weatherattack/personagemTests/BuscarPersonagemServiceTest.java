package br.com.cwi.crescer.weatherattack.personagemTests;

import br.com.cwi.crescer.weatherattack.dominio.Personagem;
import br.com.cwi.crescer.weatherattack.repository.PersonagemDelegate;
import br.com.cwi.crescer.weatherattack.service.personagem.BuscarPersonagemPorIdUsuarioService;
import br.com.cwi.crescer.weatherattack.service.personagem.BuscarPersonagemService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.runners.MockitoJUnitRunner;

import java.util.Optional;

@RunWith(MockitoJUnitRunner.class)
public class BuscarPersonagemServiceTest {

    @InjectMocks
    private BuscarPersonagemService tested;

    @Mock
    private PersonagemDelegate repository;

    @Test
    public void devePassarPorFindById(){
        Personagem personagem = new Personagem();
        Mockito.when(repository.findById(1L)).thenReturn(Optional.of(new Personagem()));
        tested.buscarPersonagemPorId(1L);
        Mockito.verify(repository, Mockito.times(1)).findById(1L);
    }
}
