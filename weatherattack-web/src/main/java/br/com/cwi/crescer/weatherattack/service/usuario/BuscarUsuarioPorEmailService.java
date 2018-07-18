package br.com.cwi.crescer.weatherattack.service.usuario;


import br.com.cwi.crescer.weatherattack.dominio.Usuario;
import br.com.cwi.crescer.weatherattack.exception.usuario.EmailInvalidoException;
import br.com.cwi.crescer.weatherattack.exception.usuario.UsuarioNaoEncontradoException;
import br.com.cwi.crescer.weatherattack.repository.UsuarioDelegate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Objects;

@Service
public class BuscarUsuarioPorEmailService {

    @Autowired
    private UsuarioDelegate repository;


    public Usuario buscarUsuarioPorEmail(String email){
        if(Objects.isNull(email)){
            throw new EmailInvalidoException();
        }
        return repository.findByEmail(email).orElseThrow(() -> new UsuarioNaoEncontradoException());
    }

}
