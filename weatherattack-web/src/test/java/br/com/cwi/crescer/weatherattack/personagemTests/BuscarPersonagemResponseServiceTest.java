package br.com.cwi.crescer.weatherattack.personagemTests;

import br.com.cwi.crescer.weatherattack.dominio.Personagem;
import br.com.cwi.crescer.weatherattack.dominio.Usuario;
import br.com.cwi.crescer.weatherattack.dto.response.UsuarioDtoResponse;
import br.com.cwi.crescer.weatherattack.repository.PersonagemDelegate;
import br.com.cwi.crescer.weatherattack.service.personagem.BuscarPersonagemResponseService;
import br.com.cwi.crescer.weatherattack.service.personagem.BuscarPersonagemService;
import br.com.cwi.crescer.weatherattack.service.usuario.BuscarUsuarioResponsePorIdService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.runners.MockitoJUnitRunner;

import java.util.Optional;

@RunWith(MockitoJUnitRunner.class)
public class BuscarPersonagemResponseServiceTest {

    @InjectMocks
    private BuscarPersonagemResponseService tested;

    @Mock
    private PersonagemDelegate repository;

    @Mock
    private BuscarUsuarioResponsePorIdService buscarUsuarioResponsePorIdService;

    @Test
    public void devePassarPorFindById(){
        Personagem personagem = new Personagem();
        Usuario usuario = new Usuario();
        usuario.setId(1L);
        personagem.setUsuario(usuario);
        UsuarioDtoResponse usuarioDtoResponse = new UsuarioDtoResponse();
        Mockito.when(repository.findById(1L)).thenReturn(Optional.of(personagem));
        Mockito.when(buscarUsuarioResponsePorIdService.buscarPorId(1L)).thenReturn(usuarioDtoResponse);
        tested.buscarPersonagemPorId(1L);
        Mockito.verify(repository, Mockito.times(1)).findById(1L);
    }
}
