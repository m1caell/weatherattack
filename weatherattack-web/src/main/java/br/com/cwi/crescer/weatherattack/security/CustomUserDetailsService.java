package br.com.cwi.crescer.weatherattack.security;

import br.com.cwi.crescer.weatherattack.dominio.Usuario;
import br.com.cwi.crescer.weatherattack.repository.UsuarioDelegate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.Optional;
import java.util.function.Supplier;

/*
 * Service para obter dados do usuário no contexto de autenticação
 */
@Service
public class CustomUserDetailsService implements UserDetailsService {

    @Autowired
    UsuarioDelegate usuarioRepository;

    @Override
    public UserDetails loadUserByUsername(String email) {
        Usuario usuario = getUser(() -> usuarioRepository.findByEmail(email));
        return UserPrincipal.create(usuario);
    }

    public UserDetails loadUserById(Long id) {
        Usuario usuario = getUser(() -> usuarioRepository.findById(id));
        return UserPrincipal.create(usuario);
    }

    private Usuario getUser(Supplier<Optional<Usuario>> supplier) {
        return supplier.get().orElseThrow(() ->
                new UsernameNotFoundException("Usuário não cadastrado")
        );
    }
}

