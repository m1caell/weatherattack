package br.com.cwi.crescer.weatherattack.personagemTests;

import br.com.cwi.crescer.weatherattack.dominio.Personagem;
import br.com.cwi.crescer.weatherattack.dominio.Usuario;
import br.com.cwi.crescer.weatherattack.repository.PersonagemDelegate;
import br.com.cwi.crescer.weatherattack.service.personagem.BuscarPersonagemPorIdUsuarioService;
import br.com.cwi.crescer.weatherattack.service.personagem.CriarNovoPersonagemService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.runners.MockitoJUnitRunner;

import java.util.Optional;

import static org.mockito.Matchers.any;

@RunWith(MockitoJUnitRunner.class)
public class CriarNovoPersonagemServiceTest {

    @InjectMocks
    private CriarNovoPersonagemService tested;

    @Mock
    private PersonagemDelegate repository;

    @Test
    public void devePassarPorFindByUsuario_Id(){
        Usuario usuario = new Usuario();
        Mockito.doNothing().when(repository).save(any());
        tested.criarPersonagem(usuario);
        Mockito.verify(repository, Mockito.times(1)).save(any());
    }
}
