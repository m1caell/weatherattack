package br.com.cwi.crescer.weatherattack.desafioTests;

import br.com.cwi.crescer.weatherattack.dominio.Usuario;
import br.com.cwi.crescer.weatherattack.dto.response.UsuarioDtoResponse;
import br.com.cwi.crescer.weatherattack.service.desafio.ListarDesafiosService;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.runners.MockitoJUnitRunner;

@RunWith(MockitoJUnitRunner.class)
public class ListarDesafiosServiceTest {

    @InjectMocks
    private ListarDesafiosService tested;

    @Test
    public void deveSalvarNaLista(){
        UsuarioDtoResponse usuario = new UsuarioDtoResponse();
        usuario.setId(1L);
        UsuarioDtoResponse usuario1 = new UsuarioDtoResponse();
        usuario1.setId(2L);
        tested.adicionarDesafio(usuario, usuario1);
        Assert.assertEquals(1, tested.getDesafios().size());
    }
}
