package br.com.cwi.crescer.weatherattack.desafioTests;

import br.com.cwi.crescer.weatherattack.dominio.Desafio;
import br.com.cwi.crescer.weatherattack.dominio.Usuario;
import br.com.cwi.crescer.weatherattack.dominio.enumerated.StatusDesafio;
import br.com.cwi.crescer.weatherattack.dto.response.UsuarioDtoResponse;
import br.com.cwi.crescer.weatherattack.repository.UsuarioDelegate;
import br.com.cwi.crescer.weatherattack.service.desafio.DesafiarUsuarioService;
import br.com.cwi.crescer.weatherattack.service.desafio.ListarDesafiosService;
import br.com.cwi.crescer.weatherattack.service.usuario.BuscarUsuarioResponsePorIdService;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.runners.MockitoJUnitRunner;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@RunWith(MockitoJUnitRunner.class)
public class DesafiarUsuarioServiceTest {

    @InjectMocks
    private DesafiarUsuarioService tested;

    @Mock
    private BuscarUsuarioResponsePorIdService buscarUsuarioResponsePorIdService;

    @Mock
    private ListarDesafiosService listarDesafiosService;

    @Test
    public void desafiarUsuario() {
        Long id = 1L;
        Long idDesafiado = 2L;

        UsuarioDtoResponse desafianteDto = new UsuarioDtoResponse();
        UsuarioDtoResponse desafiadoDto = new UsuarioDtoResponse();

        Desafio desafio = new Desafio(desafianteDto, desafiadoDto);
        List<Desafio> listaDesafios = new ArrayList<>();

        Mockito.when(buscarUsuarioResponsePorIdService.buscarPorId(id)).thenReturn(desafianteDto);
        Mockito.when(buscarUsuarioResponsePorIdService.buscarPorId(idDesafiado)).thenReturn(desafiadoDto);
        Mockito.when(listarDesafiosService.getDesafios()).thenReturn(listaDesafios);
        tested.desafiarUsuario(id, idDesafiado);

        Assert.assertEquals(desafio.getStatusDesafio(), StatusDesafio.PENDENTE);
    }
}