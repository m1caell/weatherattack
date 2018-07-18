package br.com.cwi.crescer.weatherattack.usuarioTests;

import br.com.cwi.crescer.weatherattack.repository.UsuarioDelegate;
import br.com.cwi.crescer.weatherattack.service.usuario.BuscarTodosUsuariosService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.runners.MockitoJUnitRunner;

@RunWith(MockitoJUnitRunner.class)
public class BuscarTodosUsuariosServiceTest {

    @InjectMocks
    private BuscarTodosUsuariosService tested;

    @Mock
    private UsuarioDelegate repository;

    @Test
    public void devePassarPorFindAll(){
        tested.buscarTodos();
        Mockito.verify(repository, Mockito.times(1)).findAll();
    }
}
