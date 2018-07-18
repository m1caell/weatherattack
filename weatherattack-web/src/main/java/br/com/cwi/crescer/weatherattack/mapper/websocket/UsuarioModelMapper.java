package br.com.cwi.crescer.weatherattack.mapper.websocket;

import static java.util.Objects.isNull;

import org.springframework.stereotype.Component;

import br.com.cwi.crescer.weatherattack.dto.websocket.UsuarioModel;
import br.com.cwi.crescer.weatherattack.security.UserPrincipal;

@Component
public class UsuarioModelMapper  {

    public UsuarioModel mapear(final UserPrincipal from) {

        if (isNull(from)) {
            return null;
        }

        return UsuarioModel.builder()
            .id(from.getId())
            .email(from.getEmail())
            .apelido(from.getApelido())
            .build();
    }

}
