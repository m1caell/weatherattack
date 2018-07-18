package br.com.cwi.crescer.weatherattack.security;

import br.com.cwi.crescer.weatherattack.dominio.Usuario;
import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Data;
import lombok.EqualsAndHashCode;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.Arrays;
import java.util.Collection;
import java.util.List;

@Data
@EqualsAndHashCode(of = "id")
public class UserPrincipal implements UserDetails {

    private static final String DEFAULT_ROLE = "ROLE_USER";

    private Long id;

    private String nome;

    private String apelido;

    private String email;

    @JsonIgnore
    private String senha;

    private Collection<? extends GrantedAuthority> authorities;

    public UserPrincipal(Long id, String nome, String apelido, String email, String senha, Collection<? extends GrantedAuthority> authorities) {
        this.id = id;
        this.nome = nome;
        this.apelido = apelido;
        this.email = email;
        this.senha = senha;
        this.authorities = authorities;
    }

    public static UserPrincipal create(Usuario usuario) {
        List<GrantedAuthority> authorities = Arrays.asList(
                new SimpleGrantedAuthority(usuario.getRole().orElse(DEFAULT_ROLE))
        );

        return new UserPrincipal(
                usuario.getId(),
                usuario.getNome(),
                usuario.getApelido(),
                usuario.getEmail(),
                usuario.getSenha(),
                authorities
        );
    }

    @Override
    public String getUsername() {
        return email;
    }

    @Override
    public String getPassword() {
        return senha;
    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return authorities;
    }

    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    @Override
    public boolean isAccountNonLocked() {
        return true;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    @Override
    public boolean isEnabled() {
        return true;
    }

}

