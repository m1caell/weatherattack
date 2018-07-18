package br.com.cwi.crescer.weatherattack.service.usuario;

import br.com.cwi.crescer.weatherattack.dominio.Usuario;
import br.com.cwi.crescer.weatherattack.exception.usuario.UsuarioNaoEncontradoException;
import br.com.cwi.crescer.weatherattack.repository.UsuarioDelegate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Objects;

@Service
public class BuscarUsuarioPorIdService {

    @Autowired
    private UsuarioDelegate repository;


    public Usuario buscarPorId(Long id){
        if(Objects.isNull(id)){
            throw new UsuarioNaoEncontradoException();
        }
        return repository.findById(id).orElseThrow(() -> new UsuarioNaoEncontradoException());
    }

}
