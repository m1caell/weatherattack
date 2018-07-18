package br.com.cwi.crescer.weatherattack.service.channel;

import br.com.cwi.crescer.weatherattack.dto.websocket.ChannelModel;
import br.com.cwi.crescer.weatherattack.exception.channel.ChannelNaoEncontradoException;
import lombok.Getter;
import lombok.Setter;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class ChannelService {

    @Getter
    @Setter
    private List<ChannelModel> listaChannel = new ArrayList<>();

    public ChannelModel procurarPorId(String id){
        return listaChannel.stream().filter(l -> l.getId().equals(id)).findFirst().orElseThrow(() -> new ChannelNaoEncontradoException());
    }

    public ChannelModel salvarChannel(ChannelModel channelModel){
        listaChannel.add(channelModel);
        return channelModel;
    }

    public List<ChannelModel> listarTodos(){
        return listaChannel;
    }


}
