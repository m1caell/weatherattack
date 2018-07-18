package br.com.cwi.crescer.weatherattack.configuration;

import static org.springframework.messaging.simp.stomp.StompCommand.DISCONNECT;

import java.security.Principal;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.messaging.Message;
import org.springframework.messaging.MessageChannel;
import org.springframework.messaging.simp.SimpMessagingTemplate;
import org.springframework.messaging.simp.stomp.StompHeaderAccessor;
import org.springframework.messaging.support.ChannelInterceptorAdapter;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Component;

import br.com.cwi.crescer.weatherattack.dto.websocket.UsuarioModel;
import br.com.cwi.crescer.weatherattack.mapper.websocket.UsuarioModelMapper;
import br.com.cwi.crescer.weatherattack.security.UserPrincipal;
import br.com.cwi.crescer.weatherattack.service.usuario.UsuarioOnlineService;

@Component
public class DisconnectedChannelInterceptor extends ChannelInterceptorAdapter {

    @Autowired
    private UsuarioModelMapper mapper;

    @Autowired
    private UsuarioOnlineService usuarioOnlineService;

    @Autowired
    private SimpMessagingTemplate brokerMessagingTemplate;

    @Override
    public void postSend(Message<?> message, MessageChannel channel, boolean sent) {
        StompHeaderAccessor stompDetails = StompHeaderAccessor.wrap(message);

        if (DISCONNECT.equals(stompDetails.getCommand())) {
            UsuarioModel usuario = map(stompDetails.getUser());
            usuarioOnlineService.disconnect(usuario);
            brokerMessagingTemplate.convertAndSend("/topic/disconnected", usuarioOnlineService.getUsuariosOnline());
        }

    }

    private UsuarioModel map(Principal auth) {
        Authentication authentication = (Authentication) auth;
        UserPrincipal userPrincipal = (UserPrincipal) authentication.getPrincipal();
        return mapper.mapear(userPrincipal);
    }
}
