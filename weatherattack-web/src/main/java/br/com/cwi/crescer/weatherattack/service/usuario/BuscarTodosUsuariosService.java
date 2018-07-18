package br.com.cwi.crescer.weatherattack.service.usuario;

import br.com.cwi.crescer.weatherattack.dominio.Usuario;
import br.com.cwi.crescer.weatherattack.repository.UsuarioDelegate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class BuscarTodosUsuariosService {

    @Autowired
    private UsuarioDelegate repository;


    public List<Usuario> buscarTodos(){
        return repository.findAll();
    }

}
