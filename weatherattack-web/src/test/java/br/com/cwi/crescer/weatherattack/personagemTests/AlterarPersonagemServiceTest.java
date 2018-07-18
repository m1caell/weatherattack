package br.com.cwi.crescer.weatherattack.personagemTests;

import br.com.cwi.crescer.weatherattack.dominio.Personagem;
import br.com.cwi.crescer.weatherattack.repository.PersonagemDelegate;
import br.com.cwi.crescer.weatherattack.service.personagem.AlterarPersonagemService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.runners.MockitoJUnitRunner;

@RunWith(MockitoJUnitRunner.class)
public class AlterarPersonagemServiceTest {

    @InjectMocks
    private AlterarPersonagemService tested;

    @Mock
    private PersonagemDelegate repository;

    @Test
    public void devePassarPorSave(){
        Personagem personagem = new Personagem();
        tested.alterar(personagem);
        Mockito.verify(repository, Mockito.times(1)).save(personagem);
    }
}
