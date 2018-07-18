package br.com.cwi.crescer.weatherattack.service.usuario;

import static java.util.Objects.nonNull;

import java.util.Collection;
import java.util.HashMap;
import java.util.Map;

import org.springframework.stereotype.Service;

import br.com.cwi.crescer.weatherattack.dto.websocket.UsuarioModel;

@Service
public class UsuarioOnlineService {

    private Map<Long, UsuarioModel> usuariosOnline = new HashMap<>();

    public Collection<UsuarioModel> getUsuariosOnline() {
        return usuariosOnline.values();
    }

    public void connect(final UsuarioModel usuarioModel) {
        if (nonNull(usuarioModel)) {
            usuariosOnline.put(usuarioModel.getId(), usuarioModel);
        }
    }

    public void disconnect(final UsuarioModel usuarioModel) {
        if (nonNull(usuarioModel)) {
            usuariosOnline.remove(usuarioModel.getId());
        }
    }
}
