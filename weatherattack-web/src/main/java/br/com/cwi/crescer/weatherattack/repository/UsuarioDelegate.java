package br.com.cwi.crescer.weatherattack.repository;

import br.com.cwi.crescer.weatherattack.dominio.Usuario;
import br.com.cwi.crescer.weatherattack.dto.response.UsuarioDtoResponse;
import org.springframework.data.repository.Repository;

import java.util.List;
import java.util.Optional;

public interface UsuarioDelegate extends Repository<Usuario, Long> {

    List<Usuario> findAll();

    void save(Usuario usuario);

    Optional<Usuario> findByEmail(String email);

    <T> Optional<Usuario> findById(Long id);

    Usuario queryById(Long id);

}
