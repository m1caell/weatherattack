package br.com.cwi.crescer.weatherattack.web.controller.usuario;

import java.util.Collection;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.handler.annotation.Payload;
import org.springframework.messaging.handler.annotation.SendTo;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import br.com.cwi.crescer.weatherattack.dominio.Usuario;
import br.com.cwi.crescer.weatherattack.dto.websocket.UsuarioModel;
import br.com.cwi.crescer.weatherattack.service.usuario.UsuarioOnlineService;
import br.com.cwi.crescer.weatherattack.service.usuario.BuscarUsuarioPorIdService;

@RestController
@RequestMapping("/usuario")
public class UsuarioController {

    @Autowired
    private BuscarUsuarioPorIdService buscarUsuarioPorIdService;

    @Autowired
    private UsuarioOnlineService usuarioOnlineService;

    @GetMapping("/{id}")
    @ResponseStatus(HttpStatus.OK)
    public Usuario buscarPorId(@PathVariable Long id){
        return buscarUsuarioPorIdService.buscarPorId(id);
    }

    @MessageMapping("/connect")
    @SendTo("/topic/connected")
    public Collection<UsuarioModel> connect(@Payload UsuarioModel usuarioModel) {
        usuarioOnlineService.connect(usuarioModel);
        return usuarioOnlineService.getUsuariosOnline();
    }

    @GetMapping(value = "/todos-usuarios")
    public ResponseEntity<Collection<UsuarioModel>> buscarTodosUsuariosOnline() {
        return ResponseEntity.ok(this.usuarioOnlineService.getUsuariosOnline());
    }
}
