package br.com.cwi.crescer.weatherattack.personagemTests;

import br.com.cwi.crescer.weatherattack.dominio.Personagem;
import br.com.cwi.crescer.weatherattack.repository.PersonagemDelegate;
import br.com.cwi.crescer.weatherattack.service.personagem.AlterarPersonagemService;
import br.com.cwi.crescer.weatherattack.service.personagem.AtualizarPersonagemService;
import br.com.cwi.crescer.weatherattack.service.personagem.BuscarPersonagemService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.runners.MockitoJUnitRunner;

import static org.mockito.Matchers.any;

@RunWith(MockitoJUnitRunner.class)
public class AtualizarPersonagemServiceTest {

    @InjectMocks
    private AtualizarPersonagemService tested;

    @Mock
    private PersonagemDelegate repository;

    @Mock
    private BuscarPersonagemService buscarPersonagemPorIdUsuarioService;

    @Test
    public void devePassarPorSave(){
        Personagem personagem = new Personagem();
        personagem.setVida(1L);
        Personagem personagem1 = new Personagem();
        Mockito.when(buscarPersonagemPorIdUsuarioService.buscarPersonagemPorId(personagem.getId())).thenReturn(personagem1);
        tested.atualizar(personagem);
        Mockito.verify(repository, Mockito.times(1)).save(any());
    }
}
