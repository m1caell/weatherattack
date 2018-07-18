package br.com.cwi.crescer.weatherattack.service.usuario;

import br.com.cwi.crescer.weatherattack.dto.request.UsuarioDtoRequest;
import br.com.cwi.crescer.weatherattack.exception.usuario.UsuarioInvalidoException;
import org.springframework.stereotype.Service;

import java.util.Objects;

@Service
public class ValidarUsuarioService {

    public void validarUsuario(UsuarioDtoRequest usuario){
        if(Objects.isNull(usuario)){
            throw new UsuarioInvalidoException();
        }
        if(Objects.isNull(usuario.getNome()) || usuario.getNome().length() > 50){
            throw new UsuarioInvalidoException();
        }
        if(Objects.isNull(usuario.getEmail()) || usuario.getEmail().length() > 50){
            throw new UsuarioInvalidoException();
        }
        if(Objects.isNull(usuario.getApelido()) || usuario.getApelido().length() > 25){
            throw new UsuarioInvalidoException();
        }
        if(Objects.isNull(usuario.getSenha()) || usuario.getSenha().length() > 128){
            throw new UsuarioInvalidoException();
        }
    }
}
