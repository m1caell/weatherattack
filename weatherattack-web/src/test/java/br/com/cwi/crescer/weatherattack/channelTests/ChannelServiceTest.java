package br.com.cwi.crescer.weatherattack.channelTests;

import br.com.cwi.crescer.weatherattack.dominio.Combate;
import br.com.cwi.crescer.weatherattack.dto.websocket.ChannelModel;
import br.com.cwi.crescer.weatherattack.service.channel.ChannelService;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.runners.MockitoJUnitRunner;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@RunWith(MockitoJUnitRunner.class)
public class ChannelServiceTest {

    @InjectMocks
    private ChannelService tested;

    @Test
    public void deveRetornarChannelQuandoProcurarPorId(){
        List<ChannelModel> lista = new ArrayList<>();
        ChannelModel channelModel = new ChannelModel("teste", new Combate(), LocalDateTime.now());
        lista.add(channelModel);

        tested.setListaChannel(lista);

        ChannelModel encontrado = tested.procurarPorId("teste");
        Assert.assertEquals(channelModel, encontrado);
    }

    @Test
    public void deveAumentarTamanhoListaQuandoInserido(){
        ChannelModel channelModel = new ChannelModel("teste", new Combate(), LocalDateTime.now());

        tested.salvarChannel(channelModel);
        List<ChannelModel> lista = tested.listarTodos();
        Assert.assertEquals(1, lista.size());
    }

    @Test
    public void deveRetornarLista(){
        List<ChannelModel> lista = tested.listarTodos();
        Assert.assertEquals(0, lista.size());
    }

}
