package br.com.cwi.crescer.weatherattack.service.usuario;

import br.com.cwi.crescer.weatherattack.dominio.Usuario;
import br.com.cwi.crescer.weatherattack.dto.response.UsuarioDtoResponse;
import br.com.cwi.crescer.weatherattack.exception.usuario.UsuarioNaoEncontradoException;
import br.com.cwi.crescer.weatherattack.repository.UsuarioDelegate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Objects;

@Service
public class BuscarUsuarioResponsePorIdService {

    @Autowired
    private UsuarioDelegate repository;


    public UsuarioDtoResponse buscarPorId(Long id){
        if(Objects.isNull(id)){
            throw new UsuarioNaoEncontradoException();
        }
        Usuario u = repository.queryById(id);
        return UsuarioDtoResponse.builder().id(u.getId()).nome(u.getNome()).email(u.getEmail()).apelido(u.getApelido()).build();
    }

}
