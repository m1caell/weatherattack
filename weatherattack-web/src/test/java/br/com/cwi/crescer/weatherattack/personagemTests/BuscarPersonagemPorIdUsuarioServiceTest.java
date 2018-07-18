package br.com.cwi.crescer.weatherattack.personagemTests;

import br.com.cwi.crescer.weatherattack.dominio.Personagem;
import br.com.cwi.crescer.weatherattack.repository.PersonagemDelegate;
import br.com.cwi.crescer.weatherattack.service.personagem.BuscarPersonagemPorIdUsuarioService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.runners.MockitoJUnitRunner;

import java.util.Optional;

@RunWith(MockitoJUnitRunner.class)
public class BuscarPersonagemPorIdUsuarioServiceTest {

    @InjectMocks
    private BuscarPersonagemPorIdUsuarioService tested;

    @Mock
    private PersonagemDelegate repository;

    @Test
    public void devePassarPorFindByUsuario_Id(){
        Personagem personagem = new Personagem();
        Mockito.when(repository.findByUsuario_Id(1L)).thenReturn(Optional.of(new Personagem()));
        tested.buscarPersonagemPorIdUsuario(1L);
        Mockito.verify(repository, Mockito.times(1)).findByUsuario_Id(1L);
    }
}
